package com.example.modules.business.domain;


import com.example.modules.business.domain.profile.BusinessLogo;
import com.example.modules.business.domain.profile.BusinessName;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class BusinessProfile {
    @Embedded
    BusinessLogo businessLogo;

    @Embedded
    BusinessName businessName;

    public BusinessLogo getBusinessLogo() {
        return businessLogo;
    }

    public void updateBusinessLogo(BusinessLogo businessLogo) {
        this.businessLogo = businessLogo;
    }

    public BusinessName getBusinessName() {
        return businessName;
    }

    public void updateBusinessName(BusinessName businessName) {
        this.businessName = businessName;
    }
}
