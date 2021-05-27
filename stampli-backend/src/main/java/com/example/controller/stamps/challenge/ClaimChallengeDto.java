package com.example.controller.stamps.challenge;

public class ClaimChallengeDto {
    Integer nonce;

    public ClaimChallengeDto(Integer nonce) {
        this.nonce = nonce;
    }

    public ClaimChallengeDto() {
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }
}
