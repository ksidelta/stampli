package com.example.service.authentication.token;

import com.auth0.jwt.JWT;
import com.example.domain.authentication.token.TokenGenerator;
import com.example.domain.authentication.user.entity.UserEntity;
import com.example.service.authentication.token.sign.AlgorithmHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Period;
import java.util.Date;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TokenVerificatorImpl implements TokenGenerator {
    protected AlgorithmHolder algorithmHolder;

    public TokenVerificatorImpl(AlgorithmHolder algorithmHolder) {
        this.algorithmHolder = algorithmHolder;
    }

    @Override
    public String createToken(UserEntity user) {
        return JWT.create()
                .withSubject(user.getId().toString()) // id of User
                .withIssuer("https://stampli.at.hsp.sh/") // page url
                .withExpiresAt(Date.from(Instant.now().plus(Period.ofWeeks(1))))
                .withIssuedAt(new Date())
                .withArrayClaim("roles", user.getRoles().toArray(new String[0]))
                .sign(algorithmHolder.getAlgorithm());
    }
}
