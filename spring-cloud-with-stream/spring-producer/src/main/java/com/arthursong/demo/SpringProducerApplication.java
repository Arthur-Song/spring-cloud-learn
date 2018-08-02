package com.arthursong.demo;

import com.arthursong.demo.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(SendService.class)
@EnableEurekaClient
@SpringBootApplication
public class SpringProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProducerApplication.class, args);
	}
}
