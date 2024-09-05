package com.rjlama.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjlama.springsecurity.entity.Users;
import com.rjlama.springsecurity.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController()
@RequestMapping("/api/user")
public class UserController {
   @Autowired
    private UserService service;

     @PostMapping("/register")
    public Users register(@RequestBody Users user)
    {
        try{
            System.err.println(user);
      return  service.register(user);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
@PostMapping("/login")

    public String login(@RequestBody Users user)
    {
        return service.verify(user);
    }
    
}
