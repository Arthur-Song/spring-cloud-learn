package com.arthursong.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/2.
 */
@Slf4j
@Service
public class ConsumeService {

    @StreamListener("myInput")
    public void receive(byte[] msg){
        log.info("Consume Msg :"+new String(msg));
    }
}
