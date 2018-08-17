package com.arthursong.demo.service;

import com.arthursong.demo.entity.Person;
import com.arthursong.demo.exception.MyException;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/8/17.
 */
@Service
public class PersonService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getPersonFallback")
    public Person person(Long id){
        //调用服务获取人员
        return restTemplate.getForObject("http://spring-hystrix-provider/person/{id}",Person.class,id);
    }

    /**
     * 回退，返回一个默认的Person
     * @param id
     * @return
     */
    public Person getPersonFallback(Long id){
        Person person = new Person();
        person.setId(id);
        person.setName("Arthur Song");
        person.setAge(18);
        person.setMessage("request error");
        return person;
    }

    /**
     * 测试配置，对3个key进行命名
     * 设置命令执行超时时间为1000毫秒
     * 设置命令执行的线程池大小为1
     */
    @HystrixCommand(
            fallbackMethod="testConfigFallback", groupKey="MyGroup",
            commandKey="MyCommandKey", threadPoolKey="MyCommandPool",
            commandProperties={
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "1000")
            },
            threadPoolProperties={
                    @HystrixProperty(name = "coreSize",
                            value = "1")
            })
    public String testConfig() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    public String testConfigFallback() {
        return "error";
    }

    /**
     * 声明了忽略MyException，如果方法抛出MyException，则不会触发回退
     */
    @HystrixCommand(ignoreExceptions = {MyException.class},
            fallbackMethod="testExceptionFallBack")
    public String testException() {
        throw new MyException();
    }

    public String testExceptionFallBack() {
        return "error";
    }
}
