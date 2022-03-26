package com.example.infrastructure.jwt.generator;

import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;

public interface TokenGenerator {
    String createToken(AbstractUserAggregate user);
}
