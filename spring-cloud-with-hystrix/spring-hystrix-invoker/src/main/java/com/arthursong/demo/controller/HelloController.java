package com.arthursong.demo.controller;


import com.arthursong.demo.client.HelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * Feign与Hystrix整合
 */
@Slf4j
@RestController
public class HelloController {

	@Autowired
	HelloClient helloClient;

	@GetMapping("/feign/hello")
	public String feignHello() {
		// hello方法会超时
		String helloResult = helloClient.hello();
		// 获取断路器
		HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
				.getInstance(HystrixCommandKey.Factory
						.asKey("HelloClient#hello()"));		
		log.info("断路器状态：" + breaker.isOpen());
		return helloResult;
	}
}
