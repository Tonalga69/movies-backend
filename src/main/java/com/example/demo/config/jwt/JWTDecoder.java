package com.example.demo.config.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component


public class JWTDecoder {


    private final String secret;
    public JWTDecoder(Dotenv dotenv) {
        this.secret = dotenv.get("JWT_SECRET");
    }

    public DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);
    }
}
