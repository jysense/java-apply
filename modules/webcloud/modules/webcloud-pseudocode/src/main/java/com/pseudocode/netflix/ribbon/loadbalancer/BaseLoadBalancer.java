package com.pseudocode.netflix.ribbon.loadbalancer;

import com.netflix.util.concurrent.ShutdownEnabledTimer;
import com.pseudocode.netflix.ribbon.core.client.IClientConfigAware;
import com.pseudocode.netflix.ribbon.core.client.config.CommonClientConfigKey;
import com.pseudocode.netflix.ribbon.core.client.config.DefaultClientConfigImpl;
import com.pseudocode.netflix.ribbon.core.client.config.IClientConfig;
import com.pseudocode.netflix.ribbon.loadbalancer.client.ClientFactory;
import com.pseudocode.netflix.ribbon.loadbalancer.client.PrimeConnections;
import com.pseudocode.netflix.ribbon.loadbalancer.client.PrimeConnections.PrimeConnectionListener;
import com.pseudocode.netflix.ribbon.loadbalancer.rule.RoundRobinRule;
import com.pseudocode.netflix.ribbon.loadbalancer.server.Server;
import com.pseudocode.netflix.ribbon.loadbalancer.server.ServerListChangeListener;
import com.pseudocode.netflix.ribbon.loadbalancer.server.ServerStatusChangeListener;
import com.google.common.collect.ImmutableList;
import com.netflix.servo.annotations.DataSourceType;
import com.netflix.servo.annotations.Monitor;
import com.netflix.servo.monitor.Counter;
import com.netflix.servo.monitor.Monitors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Collections.singleton;

public class BaseLoadBalancer extends AbstractLoadBalancer implements PrimeConnectionListener, IClientConfigAware {

    private static Logger logger = LoggerFactory.getLogger(BaseLoadBalancer.class);

    private final static IRule DEFAULT_RULE = new RoundRobinRule();
    private final static SerialPingStrategy DEFAULT_PING_STRATEGY = new SerialPingStrategy();
    private static final String DEFAULT_NAME = "default";
    private static final String PREFIX = "LoadBalancer_";

    protected IRule rule = DEFAULT_RULE;

    protected IPingStrategy pingStrategy = DEFAULT_PING_STRATEGY;

    protected IPing ping = null;

    //所有注册的服务实例的列表
    @Monitor(name = PREFIX + "AllServerList", type = DataSourceType.INFORMATIONAL)
    protected volatile List<Server> allServerList = Collections.synchronizedList(new ArrayList<Server>());

    //状态为正常的注册的服务实例的列表
    @Monitor(name = PREFIX + "UpServerList", type = DataSourceType.INFORMATIONAL)
    protected volatile List<Server> upServerList = Collections.synchronizedList(new ArrayList<Server>());

    //读写锁
    protected ReadWriteLock allServerLock = new ReentrantReadWriteLock();
    protected ReadWriteLock upServerLock = new ReentrantReadWriteLock();

    protected String name = DEFAULT_NAME;

    protected Timer lbTimer = null;
    protected int pingIntervalSeconds = 10;
    protected int maxTotalPingTimeSeconds = 5;
    protected Comparator<Server> serverComparator = new ServerComparator();

    protected AtomicBoolean pingInProgress = new AtomicBoolean(false);

    protected LoadBalancerStats lbStats;

    private volatile Counter counter = Monitors.newCounter("LoadBalancer_ChooseServer");

    private PrimeConnections primeConnections;

    private volatile boolean enablePrimingConnections = false;

    private IClientConfig config;

    private List<ServerListChangeListener> changeListeners = new CopyOnWriteArrayList<ServerListChangeListener>();

    private List<ServerStatusChangeListener> serverStatusListeners = new CopyOnWriteArrayList<ServerStatusChangeListener>();

    public BaseLoadBalancer() {
        this.name = DEFAULT_NAME;
        this.ping = null;
        setRule(DEFAULT_RULE);
        //定时检查Server是否健康的任务
        setupPingTask();
        lbStats = new LoadBalancerStats(DEFAULT_NAME);
    }

    public BaseLoadBalancer(String lbName, IRule rule, LoadBalancerStats lbStats) {
        this(lbName, rule, lbStats, null);
    }

    public BaseLoadBalancer(IPing ping, IRule rule) {
        this(DEFAULT_NAME, rule, new LoadBalancerStats(DEFAULT_NAME), ping);
    }

    public BaseLoadBalancer(IPing ping, IRule rule, IPingStrategy pingStrategy) {
        this(DEFAULT_NAME, rule, new LoadBalancerStats(DEFAULT_NAME), ping, pingStrategy);
    }

    public BaseLoadBalancer(String name, IRule rule, LoadBalancerStats stats,
                            IPing ping) {
        this(name, rule, stats, ping, DEFAULT_PING_STRATEGY);
    }

    public BaseLoadBalancer(String name, IRule rule, LoadBalancerStats stats,
                            IPing ping, IPingStrategy pingStrategy) {

        logger.debug("LoadBalancer [{}]:  initialized", name);

        this.name = name;
        this.ping = ping;
        this.pingStrategy = pingStrategy;
        setRule(rule);
        setupPingTask();
        lbStats = stats;
        init();
    }

    public BaseLoadBalancer(IClientConfig config) {
        initWithNiwsConfig(config);
    }

    public BaseLoadBalancer(IClientConfig config, IRule rule, IPing ping) {
        initWithConfig(config, rule, ping);
    }

    void initWithConfig(IClientConfig clientConfig, IRule rule, IPing ping) {
        this.config = clientConfig;
        String clientName = clientConfig.getClientName();
        this.name = clientName;
        int pingIntervalTime = Integer.parseInt(""
                + clientConfig.getProperty(
                CommonClientConfigKey.NFLoadBalancerPingInterval,
                Integer.parseInt("30")));
        int maxTotalPingTime = Integer.parseInt(""
                + clientConfig.getProperty(
                CommonClientConfigKey.NFLoadBalancerMaxTotalPingTime,
                Integer.parseInt("2")));

        setPingInterval(pingIntervalTime);
        setMaxTotalPingTime(maxTotalPingTime);

        // cross associate with each other
        // i.e. Rule,Ping meet your container LB
        // LB, these are your Ping and Rule guys ...
        setRule(rule);
        setPing(ping);
        setLoadBalancerStats(new LoadBalancerStats(clientName));
        rule.setLoadBalancer(this);
        if (ping instanceof AbstractLoadBalancerPing) {
            ((AbstractLoadBalancerPing) ping).setLoadBalancer(this);
        }
        logger.info("Client: {} instantiated a LoadBalancer: {}", name, this);
        boolean enablePrimeConnections = clientConfig.get(
                CommonClientConfigKey.EnablePrimeConnections, DefaultClientConfigImpl.DEFAULT_ENABLE_PRIME_CONNECTIONS);

        if (enablePrimeConnections) {
            this.setEnablePrimingConnections(true);
            PrimeConnections primeConnections = new PrimeConnections(this.getName(), clientConfig);
            this.setPrimeConnections(primeConnections);
        }
        init();

    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        String ruleClassName = (String) clientConfig
                .getProperty(CommonClientConfigKey.NFLoadBalancerRuleClassName);
        String pingClassName = (String) clientConfig
                .getProperty(CommonClientConfigKey.NFLoadBalancerPingClassName);

        IRule rule;
        IPing ping;
        try {
            rule = (IRule) ClientFactory.instantiateInstanceWithClientConfig(
                    ruleClassName, clientConfig);
            ping = (IPing) ClientFactory.instantiateInstanceWithClientConfig(
                    pingClassName, clientConfig);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing load balancer", e);
        }
        initWithConfig(clientConfig, rule, ping);
    }

    public void addServerListChangeListener(ServerListChangeListener listener) {
        changeListeners.add(listener);
    }

    public void removeServerListChangeListener(ServerListChangeListener listener) {
        changeListeners.remove(listener);
    }

    public void addServerStatusChangeListener(ServerStatusChangeListener listener) {
        serverStatusListeners.add(listener);
    }

    public void removeServerStatusChangeListener(ServerStatusChangeListener listener) {
        serverStatusListeners.remove(listener);
    }

    public IClientConfig getClientConfig() {
        return config;
    }

    private boolean canSkipPing() {
        if (ping == null
                || ping.getClass().getName().equals(DummyPing.class.getName())) {
            // default ping, no need to set up timer
            return true;
        } else {
            return false;
        }
    }

    //定时检查Server是否健康的任务,默认的执行间隔为：10秒
    void setupPingTask() {
        if (canSkipPing()) {
            return;
        }
        if (lbTimer != null) {
            lbTimer.cancel();
        }
        lbTimer = new ShutdownEnabledTimer("NFLoadBalancer-PingTimer-" + name, true);
        lbTimer.schedule(new PingTask(), 0, pingIntervalSeconds * 1000);
        forceQuickPing();
    }

    void setName(String name) {
        // and register
        this.name = name;
        if (lbStats == null) {
            lbStats = new LoadBalancerStats(name);
        } else {
            lbStats.setName(name);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public LoadBalancerStats getLoadBalancerStats() {
        return lbStats;
    }

    public void setLoadBalancerStats(LoadBalancerStats lbStats) {
        this.lbStats = lbStats;
    }

    public Lock lockAllServerList(boolean write) {
        Lock aproposLock = write ? allServerLock.writeLock() : allServerLock
                .readLock();
        aproposLock.lock();
        return aproposLock;
    }

    public Lock lockUpServerList(boolean write) {
        Lock aproposLock = write ? upServerLock.writeLock() : upServerLock
                .readLock();
        aproposLock.lock();
        return aproposLock;
    }

    public void setPingInterval(int pingIntervalSeconds) {
        if (pingIntervalSeconds < 1) {
            return;
        }

        this.pingIntervalSeconds = pingIntervalSeconds;
        if (logger.isDebugEnabled()) {
            logger.debug("LoadBalancer [{}]:  pingIntervalSeconds set to {}",
                    name, this.pingIntervalSeconds);
        }
        setupPingTask(); // since ping data changed
    }

    public int getPingInterval() {
        return pingIntervalSeconds;
    }

    public void setMaxTotalPingTime(int maxTotalPingTimeSeconds) {
        if (maxTotalPingTimeSeconds < 1) {
            return;
        }
        this.maxTotalPingTimeSeconds = maxTotalPingTimeSeconds;
        logger.debug("LoadBalancer [{}]: maxTotalPingTime set to {}", name, this.maxTotalPingTimeSeconds);

    }

    public int getMaxTotalPingTime() {
        return maxTotalPingTimeSeconds;
    }

    public IPing getPing() {
        return ping;
    }

    public IRule getRule() {
        return rule;
    }

    public boolean isPingInProgress() {
        return pingInProgress.get();
    }

    public void setPing(IPing ping) {
        if (ping != null) {
            if (!ping.equals(this.ping)) {
                this.ping = ping;
                setupPingTask(); // since ping data changed
            }
        } else {
            this.ping = null;
            // cancel the timer task
            lbTimer.cancel();
        }
    }

    public void setRule(IRule rule) {
        if (rule != null) {
            this.rule = rule;
        } else {
            /* default rule */
            this.rule = new RoundRobinRule();
        }
        if (this.rule.getLoadBalancer() != this) {
            this.rule.setLoadBalancer(this);
        }
    }

    public int getServerCount(boolean onlyAvailable) {
        if (onlyAvailable) {
            return upServerList.size();
        } else {
            return allServerList.size();
        }
    }

    public void addServer(Server newServer) {
        if (newServer != null) {
            try {
                ArrayList<Server> newList = new ArrayList<Server>();

                newList.addAll(allServerList);
                newList.add(newServer);
                setServersList(newList);
            } catch (Exception e) {
                logger.error("LoadBalancer [{}]: Error adding newServer {}", name, newServer.getHost(), e);
            }
        }
    }

    @Override
    public void addServers(List<Server> newServers) {
        if (newServers != null && newServers.size() > 0) {
            try {
                ArrayList<Server> newList = new ArrayList<Server>();
                newList.addAll(allServerList);
                newList.addAll(newServers);
                setServersList(newList);
            } catch (Exception e) {
                logger.error("LoadBalancer [{}]: Exception while adding Servers", name, e);
            }
        }
    }

    void addServers(Object[] newServers) {
        if ((newServers != null) && (newServers.length > 0)) {

            try {
                ArrayList<Server> newList = new ArrayList<Server>();
                newList.addAll(allServerList);

                for (Object server : newServers) {
                    if (server != null) {
                        if (server instanceof String) {
                            server = new Server((String) server);
                        }
                        if (server instanceof Server) {
                            newList.add((Server) server);
                        }
                    }
                }
                setServersList(newList);
            } catch (Exception e) {
                logger.error("LoadBalancer [{}]: Exception while adding Servers", name, e);
            }
        }
    }

    public void setServersList(List lsrv) {
        Lock writeLock = allServerLock.writeLock();
        logger.debug("LoadBalancer [{}]: clearing server list (SET op)", name);

        ArrayList<Server> newServers = new ArrayList<Server>();
        writeLock.lock();
        try {
            ArrayList<Server> allServers = new ArrayList<Server>();
            for (Object server : lsrv) {
                if (server == null) {
                    continue;
                }

                if (server instanceof String) {
                    server = new Server((String) server);
                }

                if (server instanceof Server) {
                    logger.debug("LoadBalancer [{}]:  addServer [{}]", name, ((Server) server).getId());
                    allServers.add((Server) server);
                } else {
                    throw new IllegalArgumentException(
                            "Type String or Server expected, instead found:"
                                    + server.getClass());
                }

            }
            boolean listChanged = false;
            if (!allServerList.equals(allServers)) {
                listChanged = true;
                if (changeListeners != null && changeListeners.size() > 0) {
                    List<Server> oldList = ImmutableList.copyOf(allServerList);
                    List<Server> newList = ImmutableList.copyOf(allServers);
                    for (ServerListChangeListener l: changeListeners) {
                        try {
                            l.serverListChanged(oldList, newList);
                        } catch (Exception e) {
                            logger.error("LoadBalancer [{}]: Error invoking server list change listener", name, e);
                        }
                    }
                }
            }
            if (isEnablePrimingConnections()) {
                for (Server server : allServers) {
                    if (!allServerList.contains(server)) {
                        server.setReadyToServe(false);
                        newServers.add((Server) server);
                    }
                }
                if (primeConnections != null) {
                    //对新加的服务进行异步连接测试
                    primeConnections.primeConnectionsAsync(newServers, this);
                }
            }
            allServerList = allServers;
            if (canSkipPing()) {
                for (Server s : allServerList) {
                    s.setAlive(true);
                }
                upServerList = allServerList;
            } else if (listChanged) {
                forceQuickPing();
            }
        } finally {
            writeLock.unlock();
        }
    }

    void setServers(String srvString) {
        if (srvString != null) {

            try {
                String[] serverArr = srvString.split(",");
                ArrayList<Server> newList = new ArrayList<Server>();

                for (String serverString : serverArr) {
                    if (serverString != null) {
                        serverString = serverString.trim();
                        if (serverString.length() > 0) {
                            Server svr = new Server(serverString);
                            newList.add(svr);
                        }
                    }
                }
                setServersList(newList);
            } catch (Exception e) {
                logger.error("LoadBalancer [{}]: Exception while adding Servers", name, e);
            }
        }
    }

    public Server getServerByIndex(int index, boolean availableOnly) {
        try {
            return (availableOnly ? upServerList.get(index) : allServerList
                    .get(index));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Server> getServerList(boolean availableOnly) {
        return (availableOnly ? getReachableServers() : getAllServers());
    }

    @Override
    public List<Server> getReachableServers() {
        return Collections.unmodifiableList(upServerList);
    }

    @Override
    public List<Server> getAllServers() {
        return Collections.unmodifiableList(allServerList);
    }

    @Override
    public List<Server> getServerList(ServerGroup serverGroup) {
        switch (serverGroup) {
            case ALL:
                return allServerList;
            case STATUS_UP:
                return upServerList;
            case STATUS_NOT_UP:
                ArrayList<Server> notAvailableServers = new ArrayList<Server>(
                        allServerList);
                ArrayList<Server> upServers = new ArrayList<Server>(upServerList);
                notAvailableServers.removeAll(upServers);
                return notAvailableServers;
        }
        return new ArrayList<Server>();
    }

    public void cancelPingTask() {
        if (lbTimer != null) {
            lbTimer.cancel();
        }
    }

    //检查Server是否健康的任务
    class PingTask extends TimerTask {
        public void run() {
            try {
                new Pinger(pingStrategy).runPinger();
            } catch (Exception e) {
                logger.error("LoadBalancer [{}]: Error pinging", name, e);
            }
        }
    }

    class Pinger {

        private final IPingStrategy pingerStrategy;

        public Pinger(IPingStrategy pingerStrategy) {
            this.pingerStrategy = pingerStrategy;
        }

        public void runPinger() throws Exception {
            if (!pingInProgress.compareAndSet(false, true)) {
                return; // Ping in progress - nothing to do
            }

            // we are "in" - we get to Ping

            Server[] allServers = null;
            boolean[] results = null;

            Lock allLock = null;
            Lock upLock = null;

            try {
                /*
                 * The readLock should be free unless an addServer operation is
                 * going on...
                 */
                allLock = allServerLock.readLock();
                allLock.lock();
                allServers = allServerList.toArray(new Server[allServerList.size()]);
                allLock.unlock();

                int numCandidates = allServers.length;
                results = pingerStrategy.pingServers(ping, allServers);

                final List<Server> newUpList = new ArrayList<Server>();
                final List<Server> changedServers = new ArrayList<Server>();

                for (int i = 0; i < numCandidates; i++) {
                    boolean isAlive = results[i];
                    Server svr = allServers[i];
                    boolean oldIsAlive = svr.isAlive();

                    svr.setAlive(isAlive);

                    if (oldIsAlive != isAlive) {
                        changedServers.add(svr);
                        logger.debug("LoadBalancer [{}]:  Server [{}] status changed to {}",
                                name, svr.getId(), (isAlive ? "ALIVE" : "DEAD"));
                    }

                    if (isAlive) {
                        newUpList.add(svr);
                    }
                }
                upLock = upServerLock.writeLock();
                upLock.lock();
                upServerList = newUpList;
                upLock.unlock();

                notifyServerStatusChangeListener(changedServers);
            } finally {
                pingInProgress.set(false);
            }
        }
    }

    private void notifyServerStatusChangeListener(final Collection<Server> changedServers) {
        if (changedServers != null && !changedServers.isEmpty() && !serverStatusListeners.isEmpty()) {
            for (ServerStatusChangeListener listener : serverStatusListeners) {
                try {
                    listener.serverStatusChanged(changedServers);
                } catch (Exception e) {
                    logger.error("LoadBalancer [{}]: Error invoking server status change listener", name, e);
                }
            }
        }
    }

    private final Counter createCounter() {
        return Monitors.newCounter("LoadBalancer_ChooseServer");
    }

    public Server chooseServer(Object key) {
        if (counter == null) {
            counter = createCounter();
        }
        counter.increment();
        if (rule == null) {
            return null;
        } else {
            try {
                return rule.choose(key);
            } catch (Exception e) {
                logger.warn("LoadBalancer [{}]:  Error choosing server for key {}", name, key, e);
                return null;
            }
        }
    }

    public String choose(Object key) {
        if (rule == null) {
            return null;
        } else {
            try {
                Server svr = rule.choose(key);
                return ((svr == null) ? null : svr.getId());
            } catch (Exception e) {
                logger.warn("LoadBalancer [{}]:  Error choosing server", name, e);
                return null;
            }
        }
    }

    public void markServerDown(Server server) {
        if (server == null || !server.isAlive()) {
            return;
        }

        logger.error("LoadBalancer [{}]:  markServerDown called on [{}]", name, server.getId());
        server.setAlive(false);
        // forceQuickPing();

        notifyServerStatusChangeListener(singleton(server));
    }

    public void markServerDown(String id) {
        boolean triggered = false;

        id = Server.normalizeId(id);

        if (id == null) {
            return;
        }

        Lock writeLock = upServerLock.writeLock();
        writeLock.lock();
        try {
            final List<Server> changedServers = new ArrayList<Server>();

            for (Server svr : upServerList) {
                if (svr.isAlive() && (svr.getId().equals(id))) {
                    triggered = true;
                    svr.setAlive(false);
                    changedServers.add(svr);
                }
            }

            if (triggered) {
                logger.error("LoadBalancer [{}]:  markServerDown called for server [{}]", name, id);
                notifyServerStatusChangeListener(changedServers);
            }

        } finally {
            writeLock.unlock();
        }
    }

    public void forceQuickPing() {
        if (canSkipPing()) {
            return;
        }
        logger.debug("LoadBalancer [{}]:  forceQuickPing invoking", name);

        try {
            new Pinger(pingStrategy).runPinger();
        } catch (Exception e) {
            logger.error("LoadBalancer [{}]: Error running forceQuickPing()", name, e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{NFLoadBalancer:name=").append(this.getName())
                .append(",current list of Servers=").append(this.allServerList)
                .append(",Load balancer stats=")
                .append(this.lbStats.toString()).append("}");
        return sb.toString();
    }

    protected void init() {
        Monitors.registerObject("LoadBalancer_" + name, this);
        // register the rule as it contains metric for available servers count
        Monitors.registerObject("Rule_" + name, this.getRule());
        if (enablePrimingConnections && primeConnections != null) {
            primeConnections.primeConnections(getReachableServers());
        }
    }

    public final PrimeConnections getPrimeConnections() {
        return primeConnections;
    }

    public final void setPrimeConnections(PrimeConnections primeConnections) {
        this.primeConnections = primeConnections;
    }

    @Override
    public void primeCompleted(Server s, Throwable lastException) {
        s.setReadyToServe(true);
    }

    public boolean isEnablePrimingConnections() {
        return enablePrimingConnections;
    }

    public final void setEnablePrimingConnections(
            boolean enablePrimingConnections) {
        this.enablePrimingConnections = enablePrimingConnections;
    }

    public void shutdown() {
        cancelPingTask();
        if (primeConnections != null) {
            primeConnections.shutdown();
        }
        Monitors.unregisterObject("LoadBalancer_" + name, this);
        Monitors.unregisterObject("Rule_" + name, this.getRule());
    }

    private static class SerialPingStrategy implements IPingStrategy {

        @Override
        public boolean[] pingServers(IPing ping, Server[] servers) {
            int numCandidates = servers.length;
            boolean[] results = new boolean[numCandidates];

            logger.debug("LoadBalancer:  PingTask executing [{}] servers configured", numCandidates);

            for (int i = 0; i < numCandidates; i++) {
                results[i] = false; /* Default answer is DEAD. */
                try {
                    if (ping != null) {
                        results[i] = ping.isAlive(servers[i]);
                    }
                } catch (Exception e) {
                    logger.error("Exception while pinging Server: '{}'", servers[i], e);
                }
            }
            return results;
        }
    }
}
