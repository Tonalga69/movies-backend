package com.example.demo.auth.controllers;


import com.example.demo.auth.entities.LoginRequest;
import com.example.demo.auth.entities.LoginResponse;
import com.example.demo.config.JWTIssuer;
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

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {



    private final JWTIssuer jwtIssuer;

    @Autowired
    private UserRepository userRepository;
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    private ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
        final User user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (user==null){
            return ResponseEntity.badRequest().body(new LoginResponse(null,"User not found"));
        }
        if (!user.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.badRequest().body(new LoginResponse("","User not found"));
        }
        final String token = jwtIssuer.issueToken(user.getId(), user.getEmail(), List.of(user.getUserRole()));
        return ResponseEntity.ok(new LoginResponse(token, "Login Successful"));
    }

    @PostMapping("/logout")
    private String logout() {
        return "Logout";
    }
}
