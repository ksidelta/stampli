package com.example.infrastructure.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.infrastructure.env.EnvironmentConfiguration;
import com.example.infrastructure.jwt.sign.AlgorithmHolder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

public class BasicJwtAuthenticationProvider implements AuthenticationProvider {
    AlgorithmHolder algorithmHolder;

    protected EnvironmentConfiguration env;

    public BasicJwtAuthenticationProvider(AlgorithmHolder algorithmHolder, EnvironmentConfiguration env) {
        this.algorithmHolder = algorithmHolder;
        this.env = env;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final BearerTokenAuthenticationToken token = (BearerTokenAuthenticationToken) authentication;
        final var textToken = token.getToken();

        try {
            DecodedJWT decodedToken = JWT.require(algorithmHolder.getAlgorithm())
                    .withIssuer(this.env.getBaseHost())
                    .build()
                    .verify(textToken);

            Integer userId = Integer.parseInt(decodedToken.getSubject());

            final var authenticated = new AbstractAuthenticationToken(Collections.emptyList()) {
                @Override
                public Object getCredentials() {
                    return userId;
                }

                @Override
                public Object getPrincipal() {
                    return userId;
                }
            };
            authenticated.setAuthenticated(true);

            return authenticated;
        } catch (JWTVerificationException jwtVerificationException) {
            throw new OAuth2AuthenticationException(new OAuth2Error("Could not verify"));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(BearerTokenAuthenticationToken.class);
    }
}
