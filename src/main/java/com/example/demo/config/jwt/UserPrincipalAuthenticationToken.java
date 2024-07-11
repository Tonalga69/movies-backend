package com.example.demo.config.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {



    private final UserPrincipal userPrincipal;
    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param userPrincipal contains the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public UserPrincipalAuthenticationToken(UserPrincipal userPrincipal) {
        super(userPrincipal.getAuthorities());
        this.userPrincipal = userPrincipal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userPrincipal;
    }
}
