package com.example.demo.auth.service;


import com.example.demo.config.jwt.JWTIssuer;
import com.example.demo.user.entities.User;
import com.example.demo.user.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthService {

      private final JWTIssuer jwtIssuer;
        private final UserRepository userRepository;

    public AuthService(JWTIssuer jwtIssuer, UserRepository userRepository) {
        this.jwtIssuer = jwtIssuer;
        this.userRepository = userRepository;
    }

    public Optional<String> login(String email, String password) {
            final User user = userRepository.findByEmail(email).orElse(null);
            if (user==null){
                return Optional.empty();
            }
            if (!user.getPassword().equals(password)){
               return  Optional.empty();
            }
            final String token= jwtIssuer.issueToken(user.getId(), user.getEmail(), List.of(user.getUserRole()));
            return Optional.of(token);
    }
}
