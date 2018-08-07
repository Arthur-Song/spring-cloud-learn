package com.arthursong.demo.controller;

import com.arthursong.demo.clients.BookClient;
import com.arthursong.demo.clients.PayClient;
import com.arthursong.demo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/8/6.
 */
@Slf4j
@RestController
public class SaleController {
    @Autowired
    private BookClient bookClient;
    @Autowired
    private PayClient payClient;

    @GetMapping("/sale/{bookId}")
    public String sale(@PathVariable("bookId") Long bookId) {
        log.info("销售模块处理销售");
        // 查找书本
        Book book = bookClient.getBook(bookId);
        // 进行支付
        payClient.doPay(new BigDecimal(10));
        log.info("销售成功，书名: " + book.getName() + ", 作者：" + book.getAuthor());
        return "销售成功，书名: " + book.getName() + ", 作者：" + book.getAuthor();
    }

    @GetMapping("/hello")
    public String hell() {
        return "Hello";
    }
}
