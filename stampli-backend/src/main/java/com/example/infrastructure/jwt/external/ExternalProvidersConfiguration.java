package com.example.infrastructure.jwt.external;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

@Configuration
public class ExternalProvidersConfiguration {

    @Bean
    @Qualifier
    JwtAuthenticationProvider googleProvider() {
        return new JwtAuthenticationProvider(JwtDecoders.fromIssuerLocation("accounts.google.com"));
    }

    @Bean
    AuthenticationManager externalAuthenticationManager(@Qualifier JwtAuthenticationProvider googleProvider) {
        return new ProviderManager(googleProvider);
    }
}
