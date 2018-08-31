package com.spring.pseudocode.beans.factory.config;

import com.spring.pseudocode.beans.BeansException;
import com.spring.pseudocode.beans.factory.AutowireCapableBeanFactory;
import com.spring.pseudocode.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;

import java.util.Iterator;

public abstract interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory
{
    public abstract void ignoreDependencyType(Class<?> paramClass);

    public abstract void ignoreDependencyInterface(Class<?> paramClass);

    public abstract void registerResolvableDependency(Class<?> paramClass, Object paramObject);

    public abstract boolean isAutowireCandidate(String paramString, DependencyDescriptor paramDependencyDescriptor)
            throws NoSuchBeanDefinitionException;

    public abstract BeanDefinition getBeanDefinition(String paramString) throws NoSuchBeanDefinitionException;

    public abstract Iterator<String> getBeanNamesIterator();

    public abstract void clearMetadataCache();

    public abstract void freezeConfiguration();

    public abstract boolean isConfigurationFrozen();

    public abstract void preInstantiateSingletons() throws BeansException;
}
