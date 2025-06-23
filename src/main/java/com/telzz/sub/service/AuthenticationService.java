package com.telzz.sub.service;

import com.telzz.sub.request.CreateUserRequest;
import com.telzz.sub.request.LoginRequest;
import com.telzz.sub.response.TokenResponse;

public interface AuthenticationService {
    TokenResponse register(CreateUserRequest request);
    TokenResponse login(LoginRequest request);
}
