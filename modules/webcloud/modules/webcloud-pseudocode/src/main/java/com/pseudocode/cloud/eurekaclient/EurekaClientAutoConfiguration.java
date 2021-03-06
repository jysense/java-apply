package com.pseudocode.cloud.eurekaclient;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import com.pseudocode.cloud.commons.client.discovery.DiscoveryClient;
import com.pseudocode.cloud.commons.util.InetUtils;
import com.pseudocode.netflix.eureka.client.appinfo.ApplicationInfoManager;
import com.pseudocode.netflix.eureka.client.appinfo.EurekaInstanceConfig;
import com.pseudocode.netflix.eureka.client.appinfo.InstanceInfo;
import com.pseudocode.netflix.eureka.client.discovery.EurekaClient;
import com.pseudocode.netflix.eureka.client.discovery.EurekaClientConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties
@ConditionalOnClass(EurekaClientConfig.class)
@Import(DiscoveryClientOptionalArgsConfiguration.class)
@ConditionalOnBean(EurekaDiscoveryClientConfiguration.Marker.class)
@ConditionalOnProperty(value = "eureka.client.enabled", matchIfMissing = true)
@AutoConfigureBefore({ NoopDiscoveryClientAutoConfiguration.class,
        CommonsClientAutoConfiguration.class, ServiceRegistryAutoConfiguration.class })
@AutoConfigureAfter(name = {"org.springframework.cloud.autoconfigure.RefreshAutoConfiguration",
        "org.springframework.cloud.netflix.eureka.EurekaDiscoveryClientConfiguration",
        "org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration"})
public class EurekaClientAutoConfiguration {

    private ConfigurableEnvironment env;

    public EurekaClientAutoConfiguration(ConfigurableEnvironment env) {
        this.env = env;
    }

    @Bean
    public HasFeatures eurekaFeature() {
        return HasFeatures.namedFeature("Eureka Client", EurekaClient.class);
    }

    @Bean
    @ConditionalOnMissingBean(value = EurekaClientConfig.class, search = SearchStrategy.CURRENT)
    public EurekaClientConfigBean eurekaClientConfigBean(ConfigurableEnvironment env) {
        EurekaClientConfigBean client = new EurekaClientConfigBean();
        if ("bootstrap".equals(this.env.getProperty("spring.config.name"))) {
            // We don't register during bootstrap by default, but there will be another
            // chance later.
            client.setRegisterWithEureka(false);
        }
        return client;
    }

    @Bean
    @ConditionalOnMissingBean
    public ManagementMetadataProvider serviceManagementMetadataProvider() {
        return new DefaultManagementMetadataProvider();
    }

    private String getProperty(String property) {
        return this.env.containsProperty(property) ? this.env.getProperty(property) : "";
    }

    @Bean
    @ConditionalOnMissingBean(value = EurekaInstanceConfig.class, search = SearchStrategy.CURRENT)
    public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils,
                                                             ManagementMetadataProvider managementMetadataProvider) {
        String hostname = getProperty("eureka.instance.hostname");
        boolean preferIpAddress = Boolean.parseBoolean(getProperty("eureka.instance.prefer-ip-address"));
        String ipAddress = getProperty("eureka.instance.ip-address");
        boolean isSecurePortEnabled = Boolean.parseBoolean(getProperty("eureka.instance.secure-port-enabled"));

        String serverContextPath = env.getProperty("server.context-path", "/");
        int serverPort = Integer.valueOf(env.getProperty("server.port", env.getProperty("port", "8080")));

        Integer managementPort = env.getProperty("management.server.port", Integer.class);// nullable. should be wrapped into optional
        String managementContextPath = env.getProperty("management.server.context-path");// nullable. should be wrapped into optional
        Integer jmxPort = env.getProperty("com.sun.management.jmxremote.port", Integer.class);//nullable
        EurekaInstanceConfigBean instance = new EurekaInstanceConfigBean(inetUtils);

        instance.setNonSecurePort(serverPort);
        instance.setInstanceId(getDefaultInstanceId(env));
        instance.setPreferIpAddress(preferIpAddress);
        instance.setSecurePortEnabled(isSecurePortEnabled);
        if (StringUtils.hasText(ipAddress)) {
            instance.setIpAddress(ipAddress);
        }

        if(isSecurePortEnabled) {
            instance.setSecurePort(serverPort);
        }

        if (StringUtils.hasText(hostname)) {
            instance.setHostname(hostname);
        }
        String statusPageUrlPath = getProperty("eureka.instance.status-page-url-path");
        String healthCheckUrlPath = getProperty("eureka.instance.health-check-url-path");

        if (StringUtils.hasText(statusPageUrlPath)) {
            instance.setStatusPageUrlPath(statusPageUrlPath);
        }
        if (StringUtils.hasText(healthCheckUrlPath)) {
            instance.setHealthCheckUrlPath(healthCheckUrlPath);
        }

        ManagementMetadata metadata = managementMetadataProvider.get(instance, serverPort,
                serverContextPath, managementContextPath, managementPort);

        if(metadata != null) {
            instance.setStatusPageUrl(metadata.getStatusPageUrl());
            instance.setHealthCheckUrl(metadata.getHealthCheckUrl());
            if(instance.isSecurePortEnabled()) {
                instance.setSecureHealthCheckUrl(metadata.getSecureHealthCheckUrl());
            }
            Map<String, String> metadataMap = instance.getMetadataMap();
            if (metadataMap.get("management.port") == null) {
                metadataMap.put("management.port", String.valueOf(metadata.getManagementPort()));
            }
        }

        setupJmxPort(instance, jmxPort);
        return instance;
    }

    private void setupJmxPort(EurekaInstanceConfigBean instance, Integer jmxPort) {
        Map<String, String> metadataMap = instance.getMetadataMap();
        if (metadataMap.get("jmx.port") == null && jmxPort != null) {
            metadataMap.put("jmx.port", String.valueOf(jmxPort));
        }
    }

    //eureka客户端
    @Bean
    public DiscoveryClient discoveryClient(EurekaInstanceConfig config, EurekaClient client) {
        return new EurekaDiscoveryClient(config, client);
    }

    @Bean
    public EurekaServiceRegistry eurekaServiceRegistry() {
        return new EurekaServiceRegistry();
    }

    @Bean
    @ConditionalOnBean(AutoServiceRegistrationProperties.class)
    @ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
    public EurekaRegistration eurekaRegistration(EurekaClient eurekaClient, CloudEurekaInstanceConfig instanceConfig, ApplicationInfoManager applicationInfoManager, ObjectProvider<HealthCheckHandler> healthCheckHandler) {
        return EurekaRegistration.builder(instanceConfig)
                .with(applicationInfoManager)
                .with(eurekaClient)
                .with(healthCheckHandler)
                .build();
    }

    @Bean
    @ConditionalOnBean(AutoServiceRegistrationProperties.class)
    @ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
    public EurekaAutoServiceRegistration eurekaAutoServiceRegistration(ApplicationContext context, EurekaServiceRegistry registry, EurekaRegistration registration) {
        return new EurekaAutoServiceRegistration(context, registry, registration);
    }

    @Configuration
    @ConditionalOnMissingRefreshScope
    protected static class EurekaClientConfiguration {

        @Autowired
        private ApplicationContext context;

        @Autowired
        private AbstractDiscoveryClientOptionalArgs<?> optionalArgs;

        @Bean(destroyMethod = "shutdown")
        @ConditionalOnMissingBean(value = EurekaClient.class, search = SearchStrategy.CURRENT)
        public EurekaClient eurekaClient(ApplicationInfoManager manager, EurekaClientConfig config) {
            return new CloudEurekaClient(manager, config, this.optionalArgs,
                    this.context);
        }

        @Bean
        @ConditionalOnMissingBean(value = ApplicationInfoManager.class, search = SearchStrategy.CURRENT)
        public ApplicationInfoManager eurekaApplicationInfoManager(EurekaInstanceConfig config) {
            InstanceInfo instanceInfo = new InstanceInfoFactory().create(config);
            return new ApplicationInfoManager(config, instanceInfo);
        }
    }

    @Configuration
    @ConditionalOnRefreshScope
    protected static class RefreshableEurekaClientConfiguration {

        @Autowired
        private ApplicationContext context;

        @Autowired
        private AbstractDiscoveryClientOptionalArgs<?> optionalArgs;

        @Bean(destroyMethod = "shutdown")
        @ConditionalOnMissingBean(value = EurekaClient.class, search = SearchStrategy.CURRENT)
        @org.springframework.cloud.context.config.annotation.RefreshScope
        @Lazy
        public EurekaClient eurekaClient(ApplicationInfoManager manager, EurekaClientConfig config, EurekaInstanceConfig instance) {
            manager.getInfo(); // force initialization
            return new CloudEurekaClient(manager, config, this.optionalArgs,
                    this.context);
        }

        @Bean
        @ConditionalOnMissingBean(value = ApplicationInfoManager.class, search = SearchStrategy.CURRENT)
        @org.springframework.cloud.context.config.annotation.RefreshScope
        @Lazy
        public ApplicationInfoManager eurekaApplicationInfoManager(EurekaInstanceConfig config) {
            InstanceInfo instanceInfo = new InstanceInfoFactory().create(config);
            return new ApplicationInfoManager(config, instanceInfo);
        }

    }

    @Target({ ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Conditional(OnMissingRefreshScopeCondition.class)
    @interface ConditionalOnMissingRefreshScope {

    }

    @Target({ ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ConditionalOnClass(RefreshScope.class)
    @ConditionalOnBean(RefreshAutoConfiguration.class)
    @interface ConditionalOnRefreshScope {

    }

    private static class OnMissingRefreshScopeCondition extends AnyNestedCondition {

        public OnMissingRefreshScopeCondition() {
            super(ConfigurationPhase.REGISTER_BEAN);
        }

        @ConditionalOnMissingClass("org.springframework.cloud.context.scope.refresh.RefreshScope")
        static class MissingClass {
        }

        @ConditionalOnMissingBean(RefreshAutoConfiguration.class)
        static class MissingScope {
        }

    }

    @Configuration
    @ConditionalOnClass(Health.class)
    protected static class EurekaHealthIndicatorConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledHealthIndicator("eureka")
        public EurekaHealthIndicator eurekaHealthIndicator(EurekaClient eurekaClient,
                                                           EurekaInstanceConfig instanceConfig, EurekaClientConfig clientConfig) {
            return new EurekaHealthIndicator(eurekaClient, instanceConfig, clientConfig);
        }
    }
}

