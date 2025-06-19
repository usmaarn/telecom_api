package com.telzz.sub.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Builder
@AllArgsConstructor
@NotBlank
@Data
public class ApiResponse {
    private Boolean success;
    private HttpStatus statusCode;
    private String message;
    private Object data;
    private Map<String, String> errors;

    public ApiResponse(Object data){
        this.success = true;
        this.statusCode = HttpStatus.OK;
        this.data = data;
    }

    public Integer getStatusCode(){
        return this.statusCode.value();
    }
}
