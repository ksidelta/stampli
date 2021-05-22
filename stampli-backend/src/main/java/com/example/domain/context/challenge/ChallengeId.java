package com.example.domain.context.challenge;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class ChallengeId implements Serializable {
    @Column(name = "business_id")
    Integer businessId;

    @Column(name = "issuer_id")
    Integer issuerId;

    public ChallengeId(Integer businessId, Integer issuerId) {
        this.businessId = businessId;
        this.issuerId = issuerId;
    }

    protected ChallengeId() {
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public Integer getIssuerId() {
        return issuerId;
    }
}
