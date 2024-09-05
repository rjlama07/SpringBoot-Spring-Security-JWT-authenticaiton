package com.rjlama.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rjlama.springsecurity.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByusername(String username);
    
}

