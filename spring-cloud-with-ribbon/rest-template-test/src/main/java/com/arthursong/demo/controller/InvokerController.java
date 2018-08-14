package com.arthursong.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class InvokerController {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 浏览器访问的请求
	 */
	@GetMapping(value = "/router", produces = MediaType.APPLICATION_JSON_VALUE)
	public String router() {
		// 根据名称来调用服务，这个URI会被拦截器所置换
		String json = restTemplate.getForObject("http://rest-template-test/hello", String.class);
		return json;
	}
	
	/**
	 * 最终的请求都会转到这个服务
	 */
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
}
