package dev.usmaarn.telecom.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "field is not valid")
    @Length(min = 3, message = "field too short")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "field is not valid")
    @Length(min = 3, message = "field too short")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[0-9]{8}$", message = "invalid phone number")
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Min(8)
    private String password;

    private String referrerId;
}
