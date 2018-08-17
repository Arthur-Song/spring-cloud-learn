package com.arthursong.demo.service;

import com.arthursong.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheService {

	@CacheResult
	@HystrixCommand
	public Person getPerson(Long id) {
		log.info("执行 getPerson 方法");
		Person p = new Person();
		p.setId(id);
		p.setName("Tom");
		return p;
	}

	/**
	 * 测试删除缓存
	 * 
	 * @param name
	 * @return
	 */
	@CacheResult()
	@HystrixCommand(commandKey = "removeKey")
	public String cacheMethod(String name) {
		log.info("执行命令");
		return "hello";
	}

	@CacheRemove(commandKey = "removeKey")
	@HystrixCommand
	public String updateMethod(String name) {
		return "update";
	}
}
