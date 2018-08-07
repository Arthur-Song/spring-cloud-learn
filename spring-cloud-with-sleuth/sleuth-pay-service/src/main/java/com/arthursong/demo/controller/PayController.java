package com.arthursong.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/8/6.
 */
@Slf4j
@RestController
public class PayController {

    @GetMapping("/pay")
    public void doPay(@RequestParam("money") BigDecimal money) {
        log.info("支付模块处理支付，金额：" + money);
    }

}
