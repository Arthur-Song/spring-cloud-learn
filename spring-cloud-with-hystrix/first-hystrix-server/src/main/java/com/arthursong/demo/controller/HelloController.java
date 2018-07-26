package com.arthursong.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/26.
 */
@RestController
public class HelloController {

    @GetMapping("/healthHello")
    public String healthHello(){
        return "hello";
    }

    @GetMapping("/errorHello")
    public String errorHello() throws Exception{
        //模拟需要处理10s
        Thread.sleep(10000);
        return "error hello";
    }
}
