package com.arthursong.demo.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */
public class MyRule implements IRule {

    private ILoadBalancer loadBalancer;

    public MyRule() {
    }

    public MyRule(ILoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    @Override
    public Server choose(Object key) {
        //获取全部的服务器
        List<Server> servers = loadBalancer. getAllServers () ;
        //只返回第一个Server 对象
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.loadBalancer=iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }
}
