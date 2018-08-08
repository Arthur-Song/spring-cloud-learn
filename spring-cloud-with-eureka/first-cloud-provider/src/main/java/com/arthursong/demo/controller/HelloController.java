package com.arthursong.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/8.
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    private Integer port;

    @GetMapping("/hello")
    public String hello(){
        return "hello provider:"+port;
    }
}
