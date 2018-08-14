package com.arthursong.demo.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by Administrator on 2018/8/14.
 */
@Slf4j
public class MyRule implements IRule {

    private ILoadBalancer loadBalancer;

    @Override
    public Server choose(Object o) {
        List<Server> servers = loadBalancer.getAllServers();
        log.info("这是自定义服务器定规则类，输出服务器信息：");
        for(Server s : servers) {
            log.info("server:" + s.getHostPort());
        }
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.loadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }
}
