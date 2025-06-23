package com.telzz.sub.service.impl;

import com.telzz.sub.entity.Token;
import com.telzz.sub.entity.User;
import com.telzz.sub.request.CreateUserRequest;
import com.telzz.sub.request.LoginRequest;
import com.telzz.sub.response.TokenResponse;
import com.telzz.sub.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationServiceImpl(
            UserService userService,
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public TokenResponse register(CreateUserRequest request){
        User user = userService.createUser(request);
        Token token = tokenService.createToken(user);

        return TokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        User user = userService.getUserByEmailAddress(request.getEmailAddress());

        if(user != null){
            Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                    request.getEmailAddress(), request.getPassword()
            );
            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);
            if(authenticationResponse.isAuthenticated()){
                Token token = tokenService.createToken(user);
                return TokenResponse.builder()
                        .accessToken(token.getAccessToken())
                        .refreshToken(token.getRefreshToken())
                        .build();
            }
        }
        return null;
    }
}
