package com.arthursong.demo.controller;

import com.arthursong.demo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/8/6.
 */
@Slf4j
@RestController
public class BookController {

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable("id") Long id) {
        log.info("书本模块查询，书本id：" + id);
        Book book = new Book();
        book.setId(id);
        book.setName("Spring Cloud基础教程");
        book.setAuthor("Arthur Song");
        return book;
    }
}
