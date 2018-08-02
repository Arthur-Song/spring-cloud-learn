package com.arthursong.demo.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Administrator on 2018/8/2.
 */
public interface ReceiveService {

    @Input("myInput")
    SubscribableChannel myInput();

}
