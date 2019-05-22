package com.kyle.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${name.test1}")
    String name1;

    @Value("${name.test2}")
    String name2;

    @GetMapping("/")
    public String hello(){
        System.out.println(name1);
        System.out.println(name2);
        return "Hello World";
    }
}
