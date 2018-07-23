package com.arthursong.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/7/23.
 */
@Slf4j
@RestController
public class InvokerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/invoker")
    public String invoker(){
        String author = restTemplate.getForObject("http://first-eureka-service-provider/author",String.class);
        log.info(author);
        return author;
    }
}
