package com.arthursong.demo.indicator;

import com.arthursong.demo.controller.HealthController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * 健康指示器
 * Created by Administrator on 2018/8/10.
 */
@Component
public class MyHealthIndicator implements HealthIndicator{

    @Override
    public Health health() {
        if(HealthController.canVisitDb) {
            // 成功连接数据库，返回UP
            return new Health.Builder(Status.UP).build();
        } else {
            // 连接数据库失败，返回 out of service
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
