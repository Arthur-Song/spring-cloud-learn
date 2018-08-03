package com.arthursong.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/3.
 */
@RestController
public class FooController {

    @Value("${foo}")
    private String foo;

    @GetMapping("/foo")
    public String foo(){
        return foo;
    }
}
