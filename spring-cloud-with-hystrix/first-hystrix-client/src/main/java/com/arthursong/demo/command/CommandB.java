package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class CommandB extends HystrixCommand<String> {

    public CommandB() {
        super(HystrixCommandGroupKey.Factory.asKey("CommandB"));
    }

    @Override
    protected String run() throws Exception {
        log.info("CommandB run 方法");
        return "";
    }
}
