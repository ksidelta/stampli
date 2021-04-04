package com.example.modules.authentication.token;

import com.auth0.jwt.JWT;
import com.example.modules.authentication.token.sign.AlgorithmHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TokenGeneratorImpl implements TokenGenerator {
    protected AlgorithmHolder algorithmHolder;

    public TokenGeneratorImpl(AlgorithmHolder algorithmHolder) {
        this.algorithmHolder = algorithmHolder;
    }

    @Override
    public String createToken(Integer id, List<String> roles) {
        return JWT.create()
                .withSubject(id.toString()) // id of User
                .withIssuer("https://stampli.at.hsp/") // page url
                .withExpiresAt(Date.from(Instant.now().plus(Period.ofWeeks(1))))
                .withIssuedAt(new Date())
                .withArrayClaim("roles", roles.toArray(new String[0]))
                .sign(algorithmHolder.getAlgorithm());
    }
}
