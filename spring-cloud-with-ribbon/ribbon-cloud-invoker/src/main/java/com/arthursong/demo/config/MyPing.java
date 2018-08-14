package com.arthursong.demo.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/8/14.
 */
@Slf4j
public class MyPing implements IPing {

    @Override
    public boolean isAlive(Server server) {
        log.info("自定义Ping类，服务器信息：" + server.getHostPort());
        return true;
    }
}
