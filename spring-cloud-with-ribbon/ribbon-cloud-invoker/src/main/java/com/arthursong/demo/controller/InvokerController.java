package com.arthursong.demo.controller;

import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/8/14.
 */
@Slf4j
@RestController
public class InvokerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private SpringClientFactory factory;

    @GetMapping(value = "/router", produces = MediaType.APPLICATION_JSON_VALUE)
    public String router() {
        // 根据名称调用服务
        String json = restTemplate.getForObject("http://ribbon-cloud-provider/person/1", String.class);
        return json;
    }

    @GetMapping(value = "/serviceInstance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceInstance uselb() {
        // 查找服务器实例
        ServiceInstance serviceInstance = loadBalancer.choose("ribbon-cloud-provider");
        return serviceInstance;
    }

    @GetMapping(value = "/defaultValue", produces = MediaType.APPLICATION_JSON_VALUE)
    public String defaultValue() {
        log.info("==== 输出默认配置：");
        // 获取默认的配置
        ZoneAwareLoadBalancer alb = (ZoneAwareLoadBalancer) factory.getLoadBalancer("default");
        log.info("IClientConfig: " + factory.getLoadBalancer("default").getClass().getName());
        log.info("IRule: " + alb.getRule().getClass().getName());
        log.info("IPing: " + alb.getPing().getClass().getName());
        log.info("ServerList: " + alb.getServerListImpl().getClass().getName());
        log.info("ServerListFilter: " + alb.getFilter().getClass().getName());
        log.info("ILoadBalancer: " + alb.getClass().getName());
        log.info("PingInterval: " + alb.getPingInterval());
        log.info("==== 输出 ribbon-cloud-provider 配置：");
        // 获取 ribbon-cloud-provider 的配置
        ZoneAwareLoadBalancer alb2 = (ZoneAwareLoadBalancer) factory.getLoadBalancer("ribbon-cloud-provider");
        log.info("IClientConfig: " + factory.getLoadBalancer("ribbon-cloud-provider").getClass().getName());
        log.info("IRule: " + alb2.getRule().getClass().getName());
        log.info("IPing: " + alb2.getPing().getClass().getName());
        log.info("ServerList: " + alb2.getServerListImpl().getClass().getName());
        log.info("ServerListFilter: " + alb2.getFilter().getClass().getName());
        log.info("ILoadBalancer: " + alb2.getClass().getName());
        log.info("PingInterval: " + alb2.getPingInterval());
        return "";
    }
}
