package com.example.infrastructure.jwt.generator;

import com.auth0.jwt.JWT;
import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;
import com.example.infrastructure.env.EnvironmentConfiguration;
import com.example.infrastructure.jwt.sign.AlgorithmHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Period;
import java.util.Date;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TokenGeneratorImpl implements TokenGenerator {
    protected AlgorithmHolder algorithmHolder;
    protected EnvironmentConfiguration environmentConfiguration;

    public TokenGeneratorImpl(AlgorithmHolder algorithmHolder, EnvironmentConfiguration environmentConfiguration) {
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
