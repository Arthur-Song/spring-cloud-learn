package com.arthursong.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/7.
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        log.info("调用业务方法");
        return "hello";
    }
}
