package com.example.infrastructure.jwt.sign;

import com.auth0.jwt.algorithms.Algorithm;

public interface AlgorithmHolder {
    Algorithm getAlgorithm();
}
