package com.telzz.sub.service.impl;

import com.telzz.sub.entity.Token;
import com.telzz.sub.entity.User;
import com.telzz.sub.repository.TokenRepository;
import com.telzz.sub.service.HashService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService {
    private final HashService hashService;
    private final TokenRepository tokenRepository;

    public TokenService(HashService hashService, TokenRepository tokenRepository){
        this.hashService = hashService;
        this.tokenRepository = tokenRepository;
    }

    public Token createToken(User user){
        String accessToken = hashService.sha256(UUID.randomUUID().toString());
        String refreshToken = hashService.sha256(UUID.randomUUID().toString());

        Token token = Token.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(LocalDateTime.now().plusHours(2))
                .refreshTokenExpireDate(LocalDateTime.now().plusHours(2))
                .build();
        return tokenRepository.save(token);
    }

    public Token findByAccessToken(String accessToken){
        return tokenRepository.findByAccessToken(accessToken).orElse(null);
    }

    public Boolean isValidToken(Token token) {
        if(token != null){
            boolean isTokenExpired = token.getAccessTokenExpireDate().isAfter(LocalDateTime.now());
            if(!isTokenExpired){
                token.setAccessTokenExpireDate(LocalDateTime.now().plusHours(2));
                return true;
            }
        }
        return false;
    }
}
