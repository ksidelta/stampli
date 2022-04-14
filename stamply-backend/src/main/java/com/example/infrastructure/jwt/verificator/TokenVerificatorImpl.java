package com.example.infrastructure.jwt.verificator;

import com.auth0.jwt.JWT;
import com.example.infrastructure.env.EnvironmentConfiguration;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.infrastructure.jwt.sign.AlgorithmHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Period;
import java.util.Date;

@Component
public class TokenVerificatorImpl implements TokenGenerator {
    protected AlgorithmHolder algorithmHolder;
    protected EnvironmentConfiguration environmentConfiguration;

    public TokenVerificatorImpl(AlgorithmHolder algorithmHolder, EnvironmentConfiguration environmentConfiguration) {
        this.algorithmHolder = algorithmHolder;
        this.environmentConfiguration = environmentConfiguration;
    }

    @Override
    public String createToken(AbstractUserAggregate user) {
        return JWT.create()
                .withSubject(user.getId().toString()) // id of User
                .withIssuer(environmentConfiguration.getBaseHost()) // page url
                .withExpiresAt(Date.from(Instant.now().plus(Period.ofWeeks(1))))
                .withIssuedAt(new Date())
                .withArrayClaim("roles", user.getRoles().toArray(new String[0]))
                .sign(algorithmHolder.getAlgorithm());
    }
}
