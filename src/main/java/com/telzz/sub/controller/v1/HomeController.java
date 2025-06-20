package com.telzz.sub.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {
    @GetMapping
    public Object homePage(){
        return Map.of("message", "Welcome to SubTelzz");
    }

    @GetMapping("/v1")
    public Object version1(){
        return Map.of("message", "Welcome to SubTelzz v1.0 API");
    }
}
