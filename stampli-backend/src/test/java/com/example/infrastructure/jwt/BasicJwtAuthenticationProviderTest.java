package com.example.infrastructure.jwt;

import com.example.domain.authentication.token.TokenGenerator;
import com.example.domain.authentication.user.entity.UserEntity;
import com.example.service.authentication.token.TokenGeneratorImpl;
import com.example.service.authentication.token.sign.InMemoryAlgorithmHolder;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

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
        final var user = new UserEntity();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
