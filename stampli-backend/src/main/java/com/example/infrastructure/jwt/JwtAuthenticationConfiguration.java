package com.example.infrastructure.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.infrastructure.env.EnvironmentConfiguration;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.infrastructure.jwt.generator.TokenGeneratorImpl;
import com.example.infrastructure.jwt.sign.AlgorithmHolder;
import com.example.infrastructure.jwt.sign.InMemoryAlgorithmHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@Configuration
public class JwtAuthenticationConfiguration {
    protected EnvironmentConfiguration env;

    public JwtAuthenticationConfiguration(EnvironmentConfiguration env) {
        this.env = env;
    }

    @Bean
    public AlgorithmHolder algorithmHolder() {
        return new InMemoryAlgorithmHolder("TWOJASTARA");
    }

    @Bean
    public TokenGenerator tokenGenerator(AlgorithmHolder algorithmHolder) {
        return new TokenGeneratorImpl(algorithmHolder, env);
    }

    @Bean
    public BasicJwtAuthenticationProvider basicJwtAuthenticationProvider(AlgorithmHolder algorithmHolder, EnvironmentConfiguration environmentConfiguration) {
        return new BasicJwtAuthenticationProvider(algorithmHolder, environmentConfiguration);
    }

    @Bean
    public JwtDecoder jwtDecoder(AlgorithmHolder algorithmHolder) {
        return (String textToken) -> {
            DecodedJWT decodedToken = JWT.require(algorithmHolder.getAlgorithm())
                    .withIssuer(env.getBaseHost())
                    .build()
                    .verify(textToken);

            return Jwt.withTokenValue(decodedToken.getToken()).build();
        };
    }
}
