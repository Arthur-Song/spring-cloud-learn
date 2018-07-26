package com.arthursong.demo.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/7/25.
 */
@Configuration
public class MyConfig {

    @Bean
    public Contract feignContract(){
        return new MyContract();
    }
}
