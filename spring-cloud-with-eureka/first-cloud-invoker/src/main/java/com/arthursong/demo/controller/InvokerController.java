package com.arthursong.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/8/8.
 */
@RestController
public class InvokerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/router")
    public String router() {
        // 根据应用名称调用服务
        String result = restTemplate.getForObject(
                "http://first-cloud-provider/hello", String.class);
        return result;
    }
}
