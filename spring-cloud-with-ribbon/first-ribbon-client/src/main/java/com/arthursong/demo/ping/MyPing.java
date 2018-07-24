package com.arthursong.demo.ping;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * Created by Administrator on 2018/7/24.
 */
public class MyPing implements IPing {

    public boolean isAlive(Server server) {
        System.out.println("这是自定义Ping实现类：" + server.getHostPort());
        return true;
    }
}
