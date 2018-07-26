package com.arthursong.demo.command;

import com.netflix.hystrix.*;

/**
 * Created by Administrator on 2018/7/26.
 */
public class TimeoutCommand extends HystrixCommand<String> {
    private int timeout;

    public TimeoutCommand() {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-key"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("command-key"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("pool-key"))
        );
    }

    public TimeoutCommand(int timeout) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeout)
                )
        );
        this.timeout = timeout;
    }

    @Override
    protected String getFallback() {
        return "error";
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(500);
        return "success";
    }
}
