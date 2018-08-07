package com.arthursong.demo.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/8/6.
 */
@FeignClient("sleuth-pay-service")
public interface PayClient {

    @GetMapping("/pay")
    void doPay(@RequestParam("money") BigDecimal money);
}
