package com.shivag.roomly.service;


import com.shivag.roomly.dto.LoginRequest;
import com.shivag.roomly.entity.User;
import com.shivag.roomly.exception.OurException;
import com.shivag.roomly.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username).orElseThrow(() -> new OurException("Username/Email not Found"));




        System.out.println("Database Password: " + user);


        return user;
    }
}
