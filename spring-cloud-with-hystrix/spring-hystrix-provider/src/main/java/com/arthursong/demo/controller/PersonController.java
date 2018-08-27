package com.arthursong.demo.controller;

import com.arthursong.demo.entity.Person;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/8/17.
 */
@RestController
public class PersonController {

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable Long id){
        Person person = new Person();
        person.setId(id);
        person.setName("Jack");
        person.setAge(22);
        person.setMessage("success");
        return person;
    }

    @GetMapping("/hello")
    public String hello() throws Exception {
        Thread.sleep(800);
        return "Hello World";
    }
}
