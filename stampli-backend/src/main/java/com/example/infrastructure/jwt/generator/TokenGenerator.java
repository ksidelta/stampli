package com.example.infrastructure.jwt.generator;

import com.example.domain.context.authentication.user.entity.UserEntity;

public interface TokenGenerator {
    String createToken(UserEntity user);
}
