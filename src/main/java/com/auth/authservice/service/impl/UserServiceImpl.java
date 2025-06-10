package com.auth.authservice.service.impl;

import com.auth.authservice.dto.UserRequest;
import com.auth.authservice.dto.UserResponse;
import com.auth.authservice.entity.User;
import com.auth.authservice.enums.Role;
import com.auth.authservice.repository.UserRepository;
import com.auth.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse userRegister(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("user already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.ROLE_COUNSELLOR)
                .build();
        User savedUser = userRepository.save(user);

      return   UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
              .build();

    }
}
