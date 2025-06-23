package com.telzz.sub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @Column(unique = true)
    private String accessToken;

    @Column(unique = true)
    private String refreshToken;

    private LocalDateTime accessTokenExpireDate;
    private LocalDateTime refreshTokenExpireDate;
}
