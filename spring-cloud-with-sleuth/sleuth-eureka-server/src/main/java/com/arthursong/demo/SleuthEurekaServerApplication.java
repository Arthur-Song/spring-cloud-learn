package com.arthursong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SleuthEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthEurekaServerApplication.class, args);
	}
}
