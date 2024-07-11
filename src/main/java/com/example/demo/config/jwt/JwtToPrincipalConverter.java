package com.example.demo.config.jwt;


import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {

    public UserPrincipal convert(DecodedJWT token) {
        return UserPrincipal.builder()
                .id(Long.parseLong(token.getSubject()))
                .email(token.getClaim("email").asString())
                .authorities(getAuthoritiesFromJWT(token))
                .build();
    }

    private List<SimpleGrantedAuthority> getAuthoritiesFromJWT(DecodedJWT token) {
        if (!token.getClaims().containsKey("roles")) {
            return List.of();
        }
        return token.getClaim("roles").asList(String.class).stream()
                .map(s -> new SimpleGrantedAuthority("ROLE_" + s))
                .toList();
    }
}
