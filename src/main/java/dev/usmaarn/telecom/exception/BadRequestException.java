package dev.usmaarn.telecom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class BadRequestException extends RuntimeException{
    private String message = "val";
    private Map<String, String> errors;

    public BadRequestException(String message){
        this.message = message;
    }

    public BadRequestException(Map<String, String> errors){
        this.message = "bad request";
        this.errors = errors;
    }
}
