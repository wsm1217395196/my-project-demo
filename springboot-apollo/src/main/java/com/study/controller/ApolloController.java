package com.study.controller;

import com.study.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApolloController {

    @Value("${test.name}")
    private String name;

    @Value("${test.age}")
    private Integer age;

    @Autowired
    private UserConfig userConfig;

    @GetMapping("/getValue")
    public UserConfig getValue() {
        System.err.println("name = " + name);
        System.err.println("age = " + age);
        System.out.println("userConfig = " + userConfig);
        return userConfig;
    }
}
