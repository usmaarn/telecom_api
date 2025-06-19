package com.telzz.sub.service.impl;

import com.telzz.sub.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtServiceImpl {
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(User user){
        String jws = Jwts.builder()
                .subject(user.getEmailAddress())
                .signWith(key)
                .compact();
        return jws;
    }
}
