package com.telzz.sub.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateUserRequest {
    @NotBlank
    @Size(min = 3, max = 50, message = "firstname is too long")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50, message = "lastname is too long")
    private String lastName;

    @NotBlank
    @Email
    private String emailAddress;

    @NotBlank
    @Size(min = 8)
    private String password;
}
