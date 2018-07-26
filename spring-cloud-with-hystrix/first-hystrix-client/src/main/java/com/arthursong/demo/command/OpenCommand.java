package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by Administrator on 2018/7/26.
 */
public class OpenCommand extends HystrixCommand<String> {
    // 设置超时的时间为500毫秒
    public OpenCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(500))
        );
    }

    protected String run() throws Exception {
        // 模拟处理超时
        Thread.sleep(800);
        return "success";
    }

    @Override
    protected String getFallback() {
        return "error";
    }
}
