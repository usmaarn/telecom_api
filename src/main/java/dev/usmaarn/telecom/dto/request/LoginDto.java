package dev.usmaarn.telecom.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Min(8)
    private String password;
}
