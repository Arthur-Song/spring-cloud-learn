package com.arthursong.demo;

import com.arthursong.demo.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value={ReceiveService.class})
@SpringBootApplication
public class SpringSecondConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecondConsumerApplication.class, args);
	}
}
