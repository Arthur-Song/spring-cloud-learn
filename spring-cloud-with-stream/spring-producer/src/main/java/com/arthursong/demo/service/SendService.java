package com.arthursong.demo.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Administrator on 2018/8/2.
 */
public interface SendService {

    @Output("myInput")
    SubscribableChannel sendOrder();
}
