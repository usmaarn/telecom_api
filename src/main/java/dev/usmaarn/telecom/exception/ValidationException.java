package dev.usmaarn.telecom.exception;

import lombok.Getter;

import java.util.Map;
public class ValidationException extends RuntimeException {
    private String message;
    @Getter
    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors){
        this.errors = errors;
    }

}
