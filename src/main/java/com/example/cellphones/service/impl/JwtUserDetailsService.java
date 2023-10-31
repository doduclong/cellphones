package com.example.cellphones.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import com.example.cellphones.exception.UserNotFoundByUsername;
import com.example.cellphones.model.User;
import com.example.cellphones.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    final private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundByUsername(username));
        return user;
    }
}