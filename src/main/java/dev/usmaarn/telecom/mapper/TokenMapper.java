package dev.usmaarn.telecom.mapper;

import dev.usmaarn.telecom.dto.response.TokenResponseDto;
import dev.usmaarn.telecom.model.Token;

public class TokenMapper {
    public static TokenResponseDto mapToDto(Token token){
        return TokenResponseDto.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
