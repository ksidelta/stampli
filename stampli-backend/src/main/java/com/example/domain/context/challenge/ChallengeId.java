package com.example.domain.context.challenge;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeId that = (ChallengeId) o;
        return Objects.equals(businessId, that.businessId) && Objects.equals(issuerId, that.issuerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessId, issuerId);
    }

    @Override
    public String toString() {
        return businessId + "-" + issuerId;
    }
}
