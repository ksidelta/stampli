package com.example.infrastructure.jwt;

import com.example.infrastructure.env.EnvironmentConfiguration;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.infrastructure.jwt.generator.TokenGeneratorImpl;
import com.example.infrastructure.jwt.sign.InMemoryAlgorithmHolder;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;

public class BasicJwtAuthenticationProviderTest {
    InMemoryAlgorithmHolder inMemoryAlgorithmHolder = new InMemoryAlgorithmHolder("XD");
    EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration();
    TokenGenerator tokenGenerator = new TokenGeneratorImpl(inMemoryAlgorithmHolder, environmentConfiguration);


    {
        environmentConfiguration.setBaseHost("https://stampli.pl");
    }

    BasicJwtAuthenticationProvider basicJwtAuthenticationProvider = new BasicJwtAuthenticationProvider(inMemoryAlgorithmHolder, environmentConfiguration);

    @Test
    public void test() {
        final var authentication = new BearerTokenAuthenticationToken(createToken());

        final var authenticatedUser = basicJwtAuthenticationProvider.authenticate(authentication);

        Integer userId = 1;
        MatcherAssert.assertThat(authenticatedUser.getPrincipal(), equalTo(userId));
    }

    public String createToken() {
        final var user = new AbstractUserAggregate();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
