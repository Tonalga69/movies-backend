package com.example.demo.auth.controllers;


import com.example.demo.auth.entities.LoginRequest;
import com.example.demo.auth.entities.LoginResponse;
import com.example.demo.auth.service.AuthService;
import com.example.demo.config.jwt.JWTIssuer;
import com.example.demo.user.entities.User;
import com.example.demo.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {



    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    private ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
        final Optional<String> token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body(new LoginResponse(null, "User not found"));
        }
        return ResponseEntity.ok(new LoginResponse(token.get(), "Login Successful"));
    }

    @PostMapping("/logout")
    private String logout() {
        return "Logout";
    }
}
