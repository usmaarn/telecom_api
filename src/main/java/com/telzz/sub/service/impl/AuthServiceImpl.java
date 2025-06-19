package com.telzz.sub.service.impl;

import com.telzz.sub.entity.User;
import com.telzz.sub.request.CreateUserRequest;
import com.telzz.sub.response.TokenResponse;
import com.telzz.sub.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtServiceImpl jwtService;

    public AuthServiceImpl(JwtServiceImpl jwtService){
        this.jwtService = jwtService;
    }

    public TokenResponse register(CreateUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmailAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        String tokenString = jwtService.generateToken(user);

        return TokenResponse.builder()
                .accessToken("tokenString")
                .refreshToken("")
                .build();
    }
}
