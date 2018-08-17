package com.arthursong.demo.client.fallback;

import com.arthursong.demo.client.HelloClient;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/17.
 */
@Component
public class HelloClientFallback implements HelloClient {
    public String hello() {
        return "error hello";
    }
}
