package com.example.infrastructure.jwt;

import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.domain.context.authentication.user.entity.UserAggregate;
import com.example.infrastructure.jwt.generator.TokenGeneratorImpl;
import com.example.infrastructure.jwt.sign.InMemoryAlgorithmHolder;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;

public class BasicJwtAuthenticationProviderTest {
    InMemoryAlgorithmHolder inMemoryAlgorithmHolder = new InMemoryAlgorithmHolder("XD");
    TokenGenerator tokenGenerator = new TokenGeneratorImpl(inMemoryAlgorithmHolder);

    BasicJwtAuthenticationProvider basicJwtAuthenticationProvider = new BasicJwtAuthenticationProvider(inMemoryAlgorithmHolder);

    @Test
    public void test() {
        final var authentication = new BearerTokenAuthenticationToken(createToken());

        final var authenticatedUser = basicJwtAuthenticationProvider.authenticate(authentication);

        Integer userId = 1;
        MatcherAssert.assertThat(authenticatedUser.getPrincipal(), equalTo(userId));
    }

    public String createToken() {
        final var user = new UserAggregate();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
