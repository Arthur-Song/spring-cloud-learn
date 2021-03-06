package com.arthursong.demo.client;

import com.arthursong.demo.config.MyUrl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2018/7/25.
 */
@FeignClient("spring-feign-provider")
public interface AuthorClient {
    @GetMapping("/author")
    public String author();

    @MyUrl(method = "GET", url = "/author")
    public String myAuthor();
}
