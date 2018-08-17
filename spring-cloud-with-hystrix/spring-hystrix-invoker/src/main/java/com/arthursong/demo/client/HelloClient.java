package com.arthursong.demo.client;

import com.arthursong.demo.client.fallback.HelloClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "spring-hystrix-provider", fallback = HelloClientFallback.class)
public interface HelloClient {

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String hello();

}
