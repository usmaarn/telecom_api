package com.telzz.sub.controller.v1;

import com.telzz.sub.constant.Constant;
import com.telzz.sub.request.CreateUserRequest;
import com.telzz.sub.response.ApiResponse;
import com.telzz.sub.response.TokenResponse;
import com.telzz.sub.service.AuthService;
import com.telzz.sub.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerHandler(@RequestBody @Valid CreateUserRequest userRequest){
        return ResponseEntity.ok("authService.register(userRequest)");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return  ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(Constant.ValidationErrors)
                        .errors(errors)
                        .build()
        );
    }

}
