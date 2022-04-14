package com.example.infrastructure.jwt.verificator;

public interface TokenVerificator {
    void verify(String token) throws IllegalArgumentException;
}
