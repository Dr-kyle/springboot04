package com.kyle.controller;

import com.kyle.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${name.test1}")
    String name1;

    @Value("${name.test2}")
    String name2;

    @Autowired
    Person person;

    @GetMapping("/")
    public String hello(){
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(person);
        return "Hello World";
    }
}
