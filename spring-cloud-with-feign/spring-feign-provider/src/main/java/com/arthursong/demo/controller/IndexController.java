package com.arthursong.demo.controller;

import com.arthursong.demo.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/23.
 */
@RestController
public class IndexController {
    @Autowired
    private Author author;
    @Value("${server.port}")
    private Integer port;

    @GetMapping("/author")
    public Author author(){
        author.setPort(port);
        return author;
    }
}
