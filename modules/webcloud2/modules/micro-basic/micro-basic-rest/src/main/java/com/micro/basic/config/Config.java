package com.micro.basic.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@MapperScan("com.micro.basic.dao")
public class Config implements EnvironmentAware {

    private static Logger logger = LoggerFactory.getLogger(Config.class);

    private static String [] activeProfiles= null;

    private static Environment environment;

    @Override
    public void setEnvironment(Environment env) {
        environment=env;
        activeProfiles = env.getActiveProfiles();
        logger.info("----------------------Application profile={}--------------------", activeProfiles);
    }

    public static String [] getActiveProfiles() {
        return activeProfiles;
    }

    public static Environment getEnvironment() {
        return environment;
    }

}
