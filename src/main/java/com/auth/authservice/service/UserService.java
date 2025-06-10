package com.auth.authservice.service;

import com.auth.authservice.dto.UserRequest;
import com.auth.authservice.dto.UserResponse;

public interface UserService {

    UserResponse userRegister(UserRequest request);

}
