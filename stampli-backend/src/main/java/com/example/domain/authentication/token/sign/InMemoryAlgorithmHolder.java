package com.example.domain.authentication.token.sign;

import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

public class InMemoryAlgorithmHolder implements AlgorithmHolder {
    protected final String secret;

    public InMemoryAlgorithmHolder(String secret) {
        this.secret = secret;
    }

    @Override
    public Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(secret);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
