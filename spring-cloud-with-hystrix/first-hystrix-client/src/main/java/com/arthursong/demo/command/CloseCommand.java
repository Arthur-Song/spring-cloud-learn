package com.arthursong.demo.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by Administrator on 2018/7/26.
 */
public class CloseCommand extends HystrixCommand<String> {

    private boolean isTimeout;

    // 设置超时的时间为500毫秒
    public CloseCommand(boolean isTimeout) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(500))
        );
        this.isTimeout = isTimeout;
    }

    @Override
    protected String run() throws Exception {
        // 让外部决定是否超时
        if(isTimeout) {
            // 模拟处理超时
            Thread.sleep(800);
        } else {
            Thread.sleep(200);
        }
        return "success";
    }

    @Override
    protected String getFallback() {
        return "error";
    }
}
