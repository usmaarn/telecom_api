package com.telzz.sub.service;

import com.telzz.sub.request.CreateUserRequest;
import com.telzz.sub.response.TokenResponse;

public interface AuthService {
    TokenResponse register(CreateUserRequest request);
}
