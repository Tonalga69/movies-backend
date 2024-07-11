package com.example.demo.auth.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class LoginResponse {
    private final String token;
}
