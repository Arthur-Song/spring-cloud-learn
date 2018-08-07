package com.arthursong.demo.clients;

import com.arthursong.demo.entity.Book;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2018/8/6.
 */
@FeignClient("sleuth-book-service")
public interface BookClient {

    @GetMapping("/book/{id}")
    Book getBook(@PathVariable("id") Long id);
}
