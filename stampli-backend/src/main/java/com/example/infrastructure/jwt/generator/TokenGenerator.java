package com.example.infrastructure.jwt.generator;

import com.example.domain.context.authentication.user.entity.UserAggregate;

public interface TokenGenerator {
    String createToken(UserAggregate user);
}
