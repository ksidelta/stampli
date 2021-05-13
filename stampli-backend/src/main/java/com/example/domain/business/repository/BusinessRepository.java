package com.example.domain.business.repository;

import com.example.domain.business.entity.business.BusinessAggregate;

public interface BusinessRepository {
    void save(BusinessAggregate businessAggregate);

    BusinessAggregate findById(Integer businessId);

    void remove(BusinessAggregate businessId);
}
