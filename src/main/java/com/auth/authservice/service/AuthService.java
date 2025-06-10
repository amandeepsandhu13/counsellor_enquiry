package com.auth.authservice.service;

import com.auth.authservice.dto.LoginRequest;

public interface AuthService {

    String login(LoginRequest request);

}
