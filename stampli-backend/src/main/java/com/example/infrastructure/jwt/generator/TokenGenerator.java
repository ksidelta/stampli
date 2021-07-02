package com.example.infrastructure.jwt.generator;

import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;

public interface TokenGenerator {
    String createToken(AbstractUserAggregate user);
}
