package com.learn.spring_security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class Student {

    private int id;
    private String name;
    private int marks;
}
