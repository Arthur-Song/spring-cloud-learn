package com.arthursong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class FirstRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstRouterApplication.class, args);
	}
}
