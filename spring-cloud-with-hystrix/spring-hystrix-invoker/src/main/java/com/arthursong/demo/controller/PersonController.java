package com.arthursong.demo.controller;

import com.arthursong.demo.entity.Person;
import com.arthursong.demo.service.CacheService;
import com.arthursong.demo.service.CollapseService;
import com.arthursong.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

/**
 * Created by Administrator on 2018/8/17.
 */
@Slf4j
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private CollapseService collapseService;

    @GetMapping("/person/{id}")
    public Person person(@PathVariable Long id){
        return personService.person(id);
    }

    @GetMapping("/testConfig")
    public String testConfig() {
        return personService.testConfig();
    }

    @GetMapping("/testException")
    public String testException() {
        String result = personService.testException();
        return result;
    }

    @GetMapping("/cache1/{id}")
    public Person testCacheResult(@PathVariable Long id) {
        // 调用多次服务
        for(int i = 0; i < 3; i++) {
            Person person = cacheService.getPerson(id);
            log.info("控制器调用服务 " + i);
        }
        return new Person();
    }

    @GetMapping(value = "/cache2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCacheRemove() {
        for(int i = 0; i < 3; i++) {
            cacheService.cacheMethod("a");
            log.info("控制器调用服务 " + i);
        }
        // 清空缓存
        cacheService.updateMethod("a");
        log.info("==========  清空了缓存");
        // 再执行多次
        for(int i = 0; i < 3; i++) {
            cacheService.cacheMethod("a");
            log.info("控制器调用服务 " + i);
        }
        return "";
    }

    @GetMapping(value = "/collapse", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCollapse() throws Exception {
        // 连续执行3次请求
        Future<Person> f1 = collapseService.getSinglePerson(1L);
        Future<Person> f2 = collapseService.getSinglePerson(2L);
        Future<Person> f3 = collapseService.getSinglePerson(3L);
        Person p1 = f1.get();
        Person p2 = f2.get();
        Person p3 = f3.get();
        log.info(p1.getId() + "---" + p1.getName());
        log.info(p2.getId() + "---" + p2.getName());
        log.info(p3.getId() + "---" + p3.getName());
        return "";
    }
}
