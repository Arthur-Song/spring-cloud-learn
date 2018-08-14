package com.arthursong.demo.controller;

import com.arthursong.demo.entity.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/8/14.
 */
@RestController
public class PersonController {

    @GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPerson(@PathVariable("id") Long id, HttpServletRequest request) {
        Person person = new Person();
        person.setId(id);
        person.setName("Arthur Song");
        person.setAge(18);
        person.setMessage(request.getRequestURL().toString());
        return person;
    }
}
