package com.rjlama.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rjlama.springsecurity.entity.Users;
import com.rjlama.springsecurity.repository.UserRepo;


@Service
public class UserService {
    @Autowired
   private  UserRepo repo;
   @Autowired 
   AuthenticationManager authenticationManager;

   @Autowired
   JwtService jwtService;
   private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder( 10 );
    public Users register(Users user) {
        String userPassword= user.getPassword();
        user.setPassword(encoder.encode(userPassword));
        return repo.save(user);
    }

    public String verify(Users user) {
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())); 

       if(authentication.isAuthenticated())
       {
              return jwtService.generateToken(user.getUsername());
         }
         else
         {
              return "User is not authenticated";
       }
    }
}
