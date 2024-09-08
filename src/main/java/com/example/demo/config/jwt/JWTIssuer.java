package com.example.demo.config.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWTIssuer {


    private final String secret;

    public JWTIssuer(Dotenv dotenv) {
        this.secret = dotenv.get("JWT_SECRET");
    }

    public String issueToken(Long id, String email, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(id))
                .withClaim("email", email)
                .withClaim("roles", roles)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 3600000))
                .sign(Algorithm.HMAC256(secret)); //dummy secret
    }
}
