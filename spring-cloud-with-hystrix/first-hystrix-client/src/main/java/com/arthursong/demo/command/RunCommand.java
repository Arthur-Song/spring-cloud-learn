package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class RunCommand extends HystrixCommand<String> {
    private String msg;

    public RunCommand(String msg) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.msg = msg;
    }

    @Override
    protected String run() throws Exception {
        log.info(msg);
        return "success";
    }

    @Override
    protected String getFallback() {
        log.warn("执行getFallback方法");
        return "error";
    }
}
