package com.example.demo.config.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class JWTDecoder {

    public DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC256("keytooolagrahakhojkarlofwknfvoqsdkokdvdnvvoqrf"))
                .build()
                .verify(token);
    }
}
