package com.example.modules.stamps.domain.command;

import java.util.Optional;

public interface StampClientAggregateRepository {
    Optional<StampClientAggregate> findByUserId(Integer id);

    void save(StampClientAggregate toSave);

    void update(StampClientAggregate toUpdate);
}
