package com.example.demo.auth.controllers;


import com.example.demo.auth.entities.LoginRequest;
import com.example.demo.auth.entities.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    private ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
        return ResponseEntity.ok(new LoginResponse("some-token", "Login Successful"));
    }

    @PostMapping("/logout")
    private String logout() {
        return "Logout";
    }
}
