package com.learn.spring_security_demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/home")
    public String getData(HttpServletRequest request){
        return "Welcome to spring security " + request.getSession().getId();
    }
}
