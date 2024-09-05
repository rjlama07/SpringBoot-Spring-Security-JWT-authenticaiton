package com.rjlama.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rjlama.springsecurity.entity.UserPrincipal;
import com.rjlama.springsecurity.entity.Users;
import com.rjlama.springsecurity.repository.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService{

  @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=repo.findByusername(username);
        if(users==null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(users);
    }
    
}
  