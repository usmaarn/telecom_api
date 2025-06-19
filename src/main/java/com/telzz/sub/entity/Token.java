package com.telzz.sub.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExpireDate;
    private LocalDateTime refreshTokenExpireDate;
}
