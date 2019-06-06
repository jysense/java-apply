package com.cloud.pseudocode.ribbon.loadbalancer.rule;

import com.cloud.pseudocode.ribbon.core.client.IClientConfigAware;
import com.cloud.pseudocode.ribbon.loadbalancer.ILoadBalancer;
import com.cloud.pseudocode.ribbon.loadbalancer.IRule;

public abstract class AbstractLoadBalancerRule implements IRule, IClientConfigAware {

    private ILoadBalancer lb;

    @Override
    public void setLoadBalancer(ILoadBalancer lb){
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer(){
        return lb;
    }
}
