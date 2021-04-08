package com.example.domain.authentication.token;

import com.example.domain.authentication.user.User;

public interface TokenGenerator {
    String createToken(User user);
}
