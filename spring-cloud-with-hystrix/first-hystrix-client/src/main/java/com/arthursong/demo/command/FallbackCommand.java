package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class FallbackCommand extends HystrixCommand<String>{

    public FallbackCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    /**
     * 断路器被强制打开，该方法不会执行
     */
    @Override
    protected String run() throws Exception {
        log.info("命令执行");
        return "success";
    }

    /**
     * 回退方法，断路器打开后会执行回退
     */
    @Override
    protected String getFallback() {
        log.warn("执行回退方法");
        return "error";
    }
}
