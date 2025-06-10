package com.auth.authservice.service.impl;

import com.auth.authservice.dto.LoginRequest;
import com.auth.authservice.entity.User;
import com.auth.authservice.repository.UserRepository;
import com.auth.authservice.service.AuthService;
import com.auth.authservice.service.UserService;
import com.auth.authservice.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("user does not exists"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("invalid credentials");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }
}
