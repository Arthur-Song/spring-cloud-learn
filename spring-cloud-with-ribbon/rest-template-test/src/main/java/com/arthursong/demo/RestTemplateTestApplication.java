package com.arthursong.demo;

import com.arthursong.demo.annotation.MyLoadBalanced;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateTestApplication {

	@Bean
	@MyLoadBalanced
	public RestTemplate getMyRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateTestApplication.class, args);
	}
}
