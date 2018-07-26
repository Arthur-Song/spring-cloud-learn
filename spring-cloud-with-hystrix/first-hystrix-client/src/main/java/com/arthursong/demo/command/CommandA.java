package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class CommandA extends HystrixCommand<String> {

    public CommandA() {
        super(HystrixCommandGroupKey.Factory.asKey("CommandA"));
    }

    @Override
    protected String run() throws Exception {
        log.info("PrimaryCommand run 方法");
        throw new RuntimeException();
    }

    @Override
    protected String getFallback() {
        return new CommandB().execute();
    }
}
