package com.arthursong.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.arthursong.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CollapseService {

	// 配置收集1秒内的请求
	@HystrixCollapser(batchMethod = "getPersons", collapserProperties = 
		{ 
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000") 
		}
	)
	public Future<Person> getSinglePerson(Long id) {
		log.info("执行单个获取的方法");
		return null;
	}

	@HystrixCommand
	public List<Person> getPersons(List<Long> ids) {
		log.info("收集请求，参数数量：" + ids.size());
		List<Person> ps = new ArrayList<Person>();
		for (Long id : ids) {
			Person p = new Person();
			p.setId(id);
			p.setName("arthur");
			ps.add(p);
		}
		return ps;
	}
}
