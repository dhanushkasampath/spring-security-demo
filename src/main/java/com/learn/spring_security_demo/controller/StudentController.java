package com.learn.spring_security_demo.controller;

import com.learn.spring_security_demo.dto.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> studentList = new ArrayList<>(List.of(
       new Student(1, "Dhanushka", 50),
       new Student(2,"kasun", 30),
       new Student(3, "nimal", 60)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentList;
    }

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        studentList.add(student);
        return student;
    }
 }
