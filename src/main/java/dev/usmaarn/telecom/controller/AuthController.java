package dev.usmaarn.telecom.controller;

import dev.usmaarn.telecom.dto.request.CreateAccountDto;
import dev.usmaarn.telecom.dto.request.LoginDto;
import dev.usmaarn.telecom.dto.response.TokenResponseDto;
import dev.usmaarn.telecom.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<TokenResponseDto> register(@RequestBody @Valid CreateAccountDto requestBody){
        TokenResponseDto responseDto = authService.register(requestBody);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginDto requestBody){
        TokenResponseDto responseDto = authService.login(requestBody);
        return ResponseEntity.ok(responseDto);
    }
}
