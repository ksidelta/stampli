package com.example.infrastructure.jwt.external;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

@Configuration
public class ExternalProvidersConfiguration {

    @Bean
    @Qualifier
    JwtAuthenticationProvider googleProvider() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation("https://accounts.google.com");

        final var issueProvider = JwtValidators.createDefaultWithIssuer("accounts.google.com");
        final var validator = new DelegatingOAuth2TokenValidator<>(issueProvider);

        jwtDecoder.setJwtValidator(validator);

        return new JwtAuthenticationProvider(jwtDecoder);
    }

    @Bean
    AuthenticationManager externalAuthenticationManager(@Qualifier JwtAuthenticationProvider googleProvider) {
        return new ProviderManager(googleProvider);
    }
}
