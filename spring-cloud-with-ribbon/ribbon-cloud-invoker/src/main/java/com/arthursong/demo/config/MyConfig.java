package com.arthursong.demo.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2018/8/14.
 */
public class MyConfig {

    @Bean
    public IRule getRule() {
        return new MyRule();
    }
    @Bean
    public IPing getPing() {
        return new MyPing();
    }
}
