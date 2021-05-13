package com.example.domain.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.domain.authentication.token.TokenGenerator;
import com.example.infrastructure.jwt.BasicJwtAuthenticationProvider;
import com.example.service.authentication.token.TokenGeneratorImpl;
import com.example.service.authentication.token.sign.AlgorithmHolder;
import com.example.service.authentication.token.sign.InMemoryAlgorithmHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class AuthenticationConfiguration {
    @Bean
    public AlgorithmHolder algorithmHolder() {
        return new InMemoryAlgorithmHolder("TWOJASTARA");
    }

    @Bean
    public TokenGenerator tokenGenerator(AlgorithmHolder algorithmHolder) {
        return new TokenGeneratorImpl(algorithmHolder);
    }

    @Bean
    public BasicJwtAuthenticationProvider basicJwtAuthenticationProvider(AlgorithmHolder algorithmHolder) {
        return new BasicJwtAuthenticationProvider(algorithmHolder);
    }

    @Bean
    public JwtDecoder jwtDecoder(AlgorithmHolder algorithmHolder) {
        return (String textToken) -> {
            DecodedJWT decodedToken = JWT.require(algorithmHolder.getAlgorithm())
                    .withIssuer("https://stampli.at.hsp.sh/")
                    .build()
                    .verify(textToken);

            return Jwt.withTokenValue(decodedToken.getToken()).build();
        };
    }
}
