package com.example.domain.context.business.repository;

import com.example.domain.context.business.entity.business.BusinessAggregate;

import java.util.Optional;

public interface BusinessRepository {
    void save(BusinessAggregate businessAggregate);

    BusinessAggregate findById(Integer businessId);

    Optional<BusinessAggregate> findByOwnerId(Integer ownerId);

    void remove(BusinessAggregate business);
}
