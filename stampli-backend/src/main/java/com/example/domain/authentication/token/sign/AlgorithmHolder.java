package com.example.domain.authentication.token.sign;

import com.auth0.jwt.algorithms.Algorithm;

public interface AlgorithmHolder {
    Algorithm getAlgorithm();
}
