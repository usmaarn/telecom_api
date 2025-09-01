package dev.usmaarn.telecom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
