package com.learn.spring_security_demo.controller;

import com.learn.spring_security_demo.entity.Users;
import com.learn.spring_security_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        return userService.register(users);
    }
}
