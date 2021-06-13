package com.example.infrastructure.jwt.verificator;

import com.auth0.jwt.JWT;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.domain.context.authentication.user.entity.UserAggregate;
import com.example.infrastructure.jwt.sign.AlgorithmHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Period;
import java.util.Date;

@Component
public class TokenVerificatorImpl implements TokenGenerator {
    protected AlgorithmHolder algorithmHolder;

    public TokenVerificatorImpl(AlgorithmHolder algorithmHolder) {
        this.algorithmHolder = algorithmHolder;
    }

    @Override
    public String createToken(UserAggregate user) {
        return JWT.create()
                .withSubject(user.getId().toString()) // id of User
                .withIssuer("https://stampli.at.hsp.sh/") // page url
                .withExpiresAt(Date.from(Instant.now().plus(Period.ofWeeks(1))))
                .withIssuedAt(new Date())
                .withArrayClaim("roles", user.getRoles().toArray(new String[0]))
                .sign(algorithmHolder.getAlgorithm());
    }
}
