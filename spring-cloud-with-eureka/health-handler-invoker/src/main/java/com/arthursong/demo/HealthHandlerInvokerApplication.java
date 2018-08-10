package com.arthursong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HealthHandlerInvokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthHandlerInvokerApplication.class, args);
	}
}
