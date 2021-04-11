package com.example.domain.authentication.token;

import com.example.domain.authentication.user.entity.User;

public interface TokenGenerator {
    String createToken(User user);
}
