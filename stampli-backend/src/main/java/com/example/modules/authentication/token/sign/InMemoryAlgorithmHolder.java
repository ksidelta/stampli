package com.example.modules.authentication.token.sign;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

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
