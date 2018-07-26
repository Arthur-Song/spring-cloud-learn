package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class CacheCommand extends HystrixCommand<String> {
    private String key;

    public CacheCommand(String key) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("MyCommandKey"))
        );
        this.key = key;
    }

    protected String run() throws Exception {
        log.info("执行命令");
        return "success";
    }

    @Override
    protected String getCacheKey() {
        return this.key;
    }
}
