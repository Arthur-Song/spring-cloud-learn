package com.arthursong.demo;

import com.arthursong.demo.command.HelloCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstHystrixClientApplicationTests {

	@Test
	public void testHealthHello() throws Exception{
		//请求正常的服务
		String url = "http://localhost:8080/healthHello";
		HelloCommand command= new HelloCommand(url);
		String result = command.execute() ;
		log.info("”请求正常的服务，结果："+result);
	}

	@Test
	public void testErrorHello() throws Exception{
		//请求异常的服务
		String url = "http://localhost:8080/errorHello";
		HelloCommand command= new HelloCommand(url);
		String result = command.execute() ;
		log.info("”请求异常的服务，结果："+result);
	}
}
