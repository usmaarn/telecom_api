package dev.usmaarn.telecom.service;

import dev.usmaarn.telecom.dto.request.CreateAccountDto;
import dev.usmaarn.telecom.dto.request.LoginDto;
import dev.usmaarn.telecom.dto.response.TokenResponseDto;
import dev.usmaarn.telecom.exception.BadRequestException;
import dev.usmaarn.telecom.mapper.TokenMapper;
import dev.usmaarn.telecom.model.Token;
import dev.usmaarn.telecom.model.User;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserService userService,
            TokenService tokenService,
            BCryptPasswordEncoder passwordEncoder
    ){
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponseDto register(CreateAccountDto createAccountDto){
        Map<String, String> errorMap = new HashMap<>();
        if (userService.existsByEmail(createAccountDto.getEmail())){
            errorMap.put("email", "email already exist");
        }
        if (userService.existsByPhoneNumber(createAccountDto.getPhoneNumber())){
            errorMap.put("phoneNumber", "phoneNumber already exist");
        }
        if(!errorMap.isEmpty()){
            throw new BadRequestException(errorMap);
        }

        User user = User.builder()
                .firstName(createAccountDto.getFirstName())
                .lastName(createAccountDto.getLastName())
                .email(createAccountDto.getEmail())
                .phoneNumber("+234".concat(createAccountDto.getPhoneNumber()))
                .password(passwordEncoder.encode(createAccountDto.getPassword()))
                .referrer(userService.findByReferrerId(createAccountDto.getReferrerId()))
                .build();
        User createdUser = userService.createUser(user);
        Token token = tokenService.createToken(createdUser);
        return TokenMapper.mapToDto(token);
    }

    public TokenResponseDto login(@Valid LoginDto requestBody) {
        User user = userService.findByEmail(requestBody.getEmail());
        if(user == null || !passwordEncoder.matches(requestBody.getPassword(), user.getPassword())){
            throw new BadRequestException("incorrect email or password");
        }

        if(!user.isAccountNonExpired() || !user.isAccountNonLocked() || !user.isEnabled()){
            throw new BadRequestException("account is disabled please contact support.");
        }

        Token token = tokenService.createToken(user);
        return TokenMapper.mapToDto(token);
    }
}
