package com.example.domain.context.stamps.command;

import java.util.Optional;

public interface StampClientAggregateRepository {
    Optional<StampClientAggregate> findByUserId(Integer id);

    void save(StampClientAggregate toSave);
}
