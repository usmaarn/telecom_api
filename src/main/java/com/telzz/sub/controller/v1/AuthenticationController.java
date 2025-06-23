package com.telzz.sub.controller.v1;

import com.telzz.sub.request.CreateUserRequest;
import com.telzz.sub.request.LoginRequest;
import com.telzz.sub.response.ApiResponse;
import com.telzz.sub.response.TokenResponse;
import com.telzz.sub.service.AuthenticationService;
import com.telzz.sub.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationServiceImpl authService){
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<Object> registerHandler(@RequestBody @Valid CreateUserRequest userRequest){
        TokenResponse tokenResponse = authService.register(userRequest);
        return ResponseEntity.ok(ApiResponse.success(tokenResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginHandler(@RequestBody @Valid LoginRequest request){
        TokenResponse tokenResponse = authService.login(request);
        if(tokenResponse == null){
            return ResponseEntity.badRequest().body(
                    ApiResponse.error("Invalid email or password")
            );
        }
        return ResponseEntity.ok(ApiResponse.success(tokenResponse));
    }
}
