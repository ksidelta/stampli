package com.example.domain.context.challenge;

public class CurrentChallenge {
    final Integer randomNonce;

    public CurrentChallenge(Integer randomNonce) {
        this.randomNonce = randomNonce;
    }

    public Integer getRandomNonce() {
        return this.randomNonce;
    }

    static CurrentChallenge emptyChallenge = new CurrentChallenge(0);
}
