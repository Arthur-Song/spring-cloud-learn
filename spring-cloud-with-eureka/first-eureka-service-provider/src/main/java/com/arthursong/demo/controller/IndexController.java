package com.arthursong.demo.controller;

import com.arthursong.demo.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/23.
 */
@RestController
public class IndexController {
    @Autowired
    private Author author;

    @GetMapping("/author")
    public Author author(){
        return author;
    }
}
