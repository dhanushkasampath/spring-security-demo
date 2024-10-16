package com.learn.spring_security_demo.repository;

import com.learn.spring_security_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}
