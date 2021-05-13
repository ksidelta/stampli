package com.example.service.authentication.token;

public interface TokenVerificator {
    void verify(String token) throws IllegalArgumentException;
}
