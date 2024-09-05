package com.rjlama.springsecurity.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rjlama.springsecurity.entity.Student;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;






@RestController
public class StudentController {

    private List<Student> students =new ArrayList<>(
        List.of(
            new Student(1, "Ramesh", 90),
            new Student(2, "Suresh", 80),
            new Student(3, "Rajesh", 70)
        )
    );
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/getCsrfToken")
    public CsrfToken getMethodName(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student postMethodName(@RequestBody Student student) {
        students.add(student);
        return student;    
    }
    
    
}
