package com.telzz.sub.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    @Email
    private String emailAddress;

    @NotBlank
    private String password;
}
