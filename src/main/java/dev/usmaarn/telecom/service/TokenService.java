package dev.usmaarn.telecom.service;

import dev.usmaarn.telecom.model.Token;
import dev.usmaarn.telecom.model.User;
import dev.usmaarn.telecom.repository.TokenRepository;
import dev.usmaarn.telecom.util.Helper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository){
        this.tokenRepository = tokenRepository;
    }

    public Token createToken(User user){
        Token token = Token.builder()
                .user(user)
                .accessToken(Helper.generateRandomString(32))
                .accessTokenExpiresAt(LocalDateTime.now().plusMinutes(30))
                .refreshToken(Helper.generateRandomString(32))
                .refreshTokenExpiresAt(LocalDateTime.now().plusHours(24))
                .build();

        return tokenRepository.save(token);
    }
}
