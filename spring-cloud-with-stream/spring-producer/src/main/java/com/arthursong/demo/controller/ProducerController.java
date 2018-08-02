package com.arthursong.demo.controller;

import com.arthursong.demo.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/2.
 */
@RestController
public class ProducerController {

    @Autowired
    private SendService sendService;

    @GetMapping("/send")
    public String sendRequest(){
        //创建消息
        Message message = MessageBuilder.withPayload("Hello World".getBytes()).build();
        //发送消息
        sendService.sendOrder().send(message);
        return "SUCCESS";
    }
}
