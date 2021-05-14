package com.example.domain.business.repository;

import com.example.domain.business.entity.business.BusinessAggregate;

import java.util.Optional;

public interface BusinessRepository {
    void save(BusinessAggregate businessAggregate);

    BusinessAggregate findById(Integer businessId);

    Optional<BusinessAggregate> findByOwnerId(Integer ownerId);

    void remove(BusinessAggregate business);
}
