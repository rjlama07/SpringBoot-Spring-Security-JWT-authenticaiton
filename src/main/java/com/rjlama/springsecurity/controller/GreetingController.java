package com.rjlama.springsecurity.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class GreetingController {

    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Hello World!" + request.getSession().getId();
    }
    
    
}
