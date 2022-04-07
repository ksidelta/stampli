package com.example.modules.business.repository;

import com.example.modules.business.domain.BusinessAggregate;

import java.util.Optional;

public interface BusinessRepository {
    void save(BusinessAggregate businessAggregate) throws DuplicatedOwnerException;

    BusinessAggregate findById(Integer businessId);

    Optional<BusinessAggregate> findByOwnerId(Integer ownerId);

    void remove(BusinessAggregate business);


    /**
     * Thrown when one user owns more than one business.
     */
    class DuplicatedOwnerException extends RuntimeException {
    }
}
