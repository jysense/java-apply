package com.pseudocode.cloud.openfeign.core;

import com.pseudocode.cloud.openfeign.core.ribbon.LoadBalancerFeignClient;
import com.pseudocode.netflix.feign.core.*;
import com.pseudocode.netflix.feign.core.Target.HardCodedTarget;
import com.pseudocode.netflix.feign.core.codec.Decoder;
import com.pseudocode.netflix.feign.core.codec.Encoder;
import com.pseudocode.netflix.feign.core.codec.ErrorDecoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

//feignclient的工厂bean
public class FeignClientFactoryBean implements FactoryBean<Object>, InitializingBean,ApplicationContextAware {

    //feignclient类名
    private Class<?> type;

    //feignclient服务名
    private String name;

    private String url;

    private String path;

    private boolean decode404;

    private ApplicationContext applicationContext;

    private Class<?> fallback = void.class;

    private Class<?> fallbackFactory = void.class;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.name, "Name must be set");
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    protected Feign.Builder feign(FeignContext context) {
        FeignLoggerFactory loggerFactory = get(context, FeignLoggerFactory.class);
        Logger logger = loggerFactory.create(this.type);

        // @formatter:off
        //调用FeignContext的getInstance方法,根据name即服务的名字来创建builder
        Feign.Builder builder = get(context, Feign.Builder.class)
                // required values
                .logger(logger)
                .encoder(get(context, Encoder.class))
                .decoder(get(context, Decoder.class))
                .contract(get(context, Contract.class));
        // @formatter:on

        configureFeign(context, builder);

        return builder;
    }

    protected void configureFeign(FeignContext context, Feign.Builder builder) {
        FeignClientProperties properties = applicationContext.getBean(FeignClientProperties.class);
        if (properties != null) {
            if (properties.isDefaultToProperties()) {
                configureUsingConfiguration(context, builder);
                configureUsingProperties(properties.getConfig().get(properties.getDefaultConfig()), builder);
                configureUsingProperties(properties.getConfig().get(this.name), builder);
            } else {
                configureUsingProperties(properties.getConfig().get(properties.getDefaultConfig()), builder);
                configureUsingProperties(properties.getConfig().get(this.name), builder);
                configureUsingConfiguration(context, builder);
            }
        } else {
            configureUsingConfiguration(context, builder);
        }
    }

    protected void configureUsingConfiguration(FeignContext context, Feign.Builder builder) {
        Logger.Level level = getOptional(context, Logger.Level.class);
        if (level != null) {
            builder.logLevel(level);
        }
        //设置重试策
        Retryer retryer = getOptional(context, Retryer.class);
        if (retryer != null) {
            builder.retryer(retryer);
        }
        //设置错误处理
        ErrorDecoder errorDecoder = getOptional(context, ErrorDecoder.class);
        if (errorDecoder != null) {
            builder.errorDecoder(errorDecoder);
        }
        Request.Options options = getOptional(context, Request.Options.class);
        if (options != null) {
            builder.options(options);
        }
        //绑定请求拦截器
        Map<String, RequestInterceptor> requestInterceptors = context.getInstances(this.name, RequestInterceptor.class);
        if (requestInterceptors != null) {
            builder.requestInterceptors(requestInterceptors.values());
        }

        if (decode404) {
            builder.decode404();
        }
    }

    protected void configureUsingProperties(FeignClientProperties.FeignClientConfiguration config, Feign.Builder builder) {
        if (config == null) {
            return;
        }

        if (config.getLoggerLevel() != null) {
            builder.logLevel(config.getLoggerLevel());
        }

        //设置到Request.Options中
        if (config.getConnectTimeout() != null && config.getReadTimeout() != null) {
            builder.options(new Request.Options(config.getConnectTimeout(), config.getReadTimeout()));
        }

        if (config.getRetryer() != null) {
            Retryer retryer = getOrInstantiate(config.getRetryer());
            builder.retryer(retryer);
        }

        if (config.getErrorDecoder() != null) {
            ErrorDecoder errorDecoder = getOrInstantiate(config.getErrorDecoder());
            builder.errorDecoder(errorDecoder);
        }

        if (config.getRequestInterceptors() != null && !config.getRequestInterceptors().isEmpty()) {
            // this will add request interceptor to builder, not replace existing
            for (Class<RequestInterceptor> bean : config.getRequestInterceptors()) {
                RequestInterceptor interceptor = getOrInstantiate(bean);
                builder.requestInterceptor(interceptor);
            }
        }

        if (config.getDecode404() != null) {
            if (config.getDecode404()) {
                builder.decode404();
            }
        }

        if (Objects.nonNull(config.getEncoder())) {
            builder.encoder(getOrInstantiate(config.getEncoder()));
        }

        if (Objects.nonNull(config.getDecoder())) {
            builder.decoder(getOrInstantiate(config.getDecoder()));
        }

        if (Objects.nonNull(config.getContract())) {
            builder.contract(getOrInstantiate(config.getContract()));
        }
    }

    private <T> T getOrInstantiate(Class<T> tClass) {
        try {
            return applicationContext.getBean(tClass);
        } catch (NoSuchBeanDefinitionException e) {
            return BeanUtils.instantiateClass(tClass);
        }
    }

    protected <T> T get(FeignContext context, Class<T> type) {
        T instance = context.getInstance(this.name, type);
        if (instance == null) {
            throw new IllegalStateException("No bean found of type " + type + " for " + this.name);
        }
        return instance;
    }

    protected <T> T getOptional(FeignContext context, Class<T> type) {
        return context.getInstance(this.name, type);
    }

    protected <T> T loadBalance(Feign.Builder builder, FeignContext context, HardCodedTarget<T> target) {
        //获得FeignClient
        Client client = getOptional(context, Client.class);
        if (client != null) {
            builder.client(client);
            Targeter targeter = get(context, Targeter.class);
            return targeter.target(this, builder, context, target);
        }
        throw new IllegalStateException("No Feign Client for loadBalancing defined. Did you forget to include spring-cloud-starter-netflix-ribbon?");
    }

    @Override
    public Object getObject() throws Exception {

        //Feign上下文对象,是feign的容器对象
        FeignContext context = applicationContext.getBean(FeignContext.class);

        //Feign.Builder用来创建feign的构建器
        Feign.Builder builder = feign(context);

        if (!StringUtils.hasText(this.url)) {
            String url;
            if (!this.name.startsWith("http")) {
                url = "http://" + this.name;
            }
            else {
                url = this.name;
            }
            //拼接成http,
            url += cleanPath();
            //创建负载均衡的代理类，底层使用到了jdk动态代理
            return loadBalance(builder, context, new HardCodedTarget<>(this.type, this.name, url));
        }
        if (StringUtils.hasText(this.url) && !this.url.startsWith("http")) {
            this.url = "http://" + this.url;
        }

        String url = this.url + cleanPath();
        Client client = getOptional(context, Client.class);
        if (client != null) {
            if (client instanceof LoadBalancerFeignClient) {
                // not lod balancing because we have a url,
                // but ribbon is on the classpath, so unwrap
                client = ((LoadBalancerFeignClient)client).getDelegate();
            }
            builder.client(client);
        }
        //创建默认的代理类
        Targeter targeter = get(context, Targeter.class);
        return targeter.target(this, builder, context, new HardCodedTarget<>(this.type, this.name, url));
    }

    private String cleanPath() {
        String path = this.path.trim();
        if (StringUtils.hasLength(path)) {
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
        }
        return path;
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDecode404() {
        return decode404;
    }

    public void setDecode404(boolean decode404) {
        this.decode404 = decode404;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Class<?> getFallback() {
        return fallback;
    }

    public void setFallback(Class<?> fallback) {
        this.fallback = fallback;
    }

    public Class<?> getFallbackFactory() {
        return fallbackFactory;
    }

    public void setFallbackFactory(Class<?> fallbackFactory) {
        this.fallbackFactory = fallbackFactory;
    }

}
