package com.arthursong.demo.handler;

import com.arthursong.demo.indicator.MyHealthIndicator;
import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/10.
 */
@Slf4j
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {
    @Autowired
    private MyHealthIndicator myHealthIndicator;

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        Status s = myHealthIndicator.health().getStatus();
        if(s.equals(Status.UP)) {
            log.info("数据库正常连接");
            return InstanceInfo.InstanceStatus.UP;
        } else {
            log.error("数据库无法连接");
            return InstanceInfo.InstanceStatus.DOWN;
        }
    }
}
