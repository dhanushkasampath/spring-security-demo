package com.learn.spring_security_demo.service;

import com.learn.spring_security_demo.entity.Users;
import com.learn.spring_security_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users register(Users users){
        return userRepository.save(users);
    }
}
