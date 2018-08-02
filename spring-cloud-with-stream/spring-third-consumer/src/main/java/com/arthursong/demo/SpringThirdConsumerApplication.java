package com.arthursong.demo;

import com.arthursong.demo.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(value={ReceiveService.class})
@SpringBootApplication
public class SpringThirdConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringThirdConsumerApplication.class, args);
	}
}
