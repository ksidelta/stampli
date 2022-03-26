package com.example.modules.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CurrentChallenge {
    @Column(name = "random_nonce")
    Integer randomNonce;

    protected CurrentChallenge() {
    }

    public CurrentChallenge(Integer randomNonce) {
        this.randomNonce = randomNonce;
    }

    public Integer nonce() {
        return randomNonce;
    }

    static CurrentChallenge emptyChallenge = new CurrentChallenge(0);
}
