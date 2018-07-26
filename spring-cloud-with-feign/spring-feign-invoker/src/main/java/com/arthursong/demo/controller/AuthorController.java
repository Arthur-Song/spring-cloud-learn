package com.arthursong.demo.controller;

import com.arthursong.demo.client.AuthorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/25.
 */
@RestController
public class AuthorController {
    @Autowired
    private AuthorClient authorClient;

    @GetMapping("/author")
    public String author(){
        return authorClient.author();
    }

    @GetMapping("/myAuthor")
    public String myAuthor(){
        return authorClient.myAuthor();
    }
}
