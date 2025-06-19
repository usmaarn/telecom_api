package com.telzz.sub.response;

import lombok.*;

@Builder
@Getter
@Setter
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
