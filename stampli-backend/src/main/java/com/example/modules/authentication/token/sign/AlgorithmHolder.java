package com.example.modules.authentication.token.sign;

import com.auth0.jwt.algorithms.Algorithm;

public interface AlgorithmHolder {
    Algorithm getAlgorithm();
}
