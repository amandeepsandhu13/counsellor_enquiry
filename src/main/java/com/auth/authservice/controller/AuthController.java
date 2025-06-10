package com.auth.authservice.controller;

import com.auth.authservice.dto.LoginRequest;
import com.auth.authservice.dto.UserRequest;
import com.auth.authservice.dto.UserResponse;
import com.auth.authservice.service.AuthService;
import com.auth.authservice.service.UserService;
import com.auth.authservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@EnableMethodSecurity
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerNewUser(@RequestBody UserRequest request){
        UserResponse response = userService.userRegister(request);
       return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public  ResponseEntity<String> login(@RequestBody LoginRequest request){
        String token = authService.login(request);
        return ResponseEntity.ok(token);

    }

    @GetMapping("/me")
    public ResponseEntity<String> getLoggedInUser(Principal principal) {
        return ResponseEntity.ok("Logged in as: " + principal.getName());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Hello Admin");
    }

}
