package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/7/26.
 */
@Slf4j
public class IsoCommand extends HystrixCommand<String> {

    private int index;

    public IsoCommand(int index) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
                .asKey("ExampleGroup")));
        this.index = index;
    }

    protected String run() throws Exception {
        Thread.sleep(500);
        log.info("执行方法，当前索引：" + index);
        return "success";
    }

    @Override
    protected String getFallback() {
        log.info("执行 fallback，当前索引：" + index);
        return "error";
    }
}
