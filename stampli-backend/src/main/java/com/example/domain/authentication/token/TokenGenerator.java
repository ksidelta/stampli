package com.example.domain.authentication.token;

import com.example.domain.authentication.user.entity.UserEntity;

public interface TokenGenerator {
    String createToken(UserEntity user);
}
