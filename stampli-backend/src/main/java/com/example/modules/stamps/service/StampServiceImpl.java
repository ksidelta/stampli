package com.example.modules.stamps.service;

import com.example.modules.stamps.domain.command.StampClientAggregate;
import com.example.modules.stamps.domain.command.StampClientAggregateRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StampServiceImpl implements StampService {
    final StampClientAggregateRepository repository;

    public StampServiceImpl(StampClientAggregateRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void createStampsAggregateForClient(Integer clientId) {
        final var aggregate = StampClientAggregate.create(clientId);

        repository.save(aggregate);
    }

    @Transactional
    @Override
    public void addStamp(Integer clientId, Integer businessId) {
        final var aggregate = repository.findByUserId(clientId).get();

        aggregate.addStampToBusiness(businessId);

        repository.update(aggregate);
    }

    @Transactional
    @Override
    public Integer getNumberOfStamps(Integer clientId, Integer businessId) {
        final var aggregate = repository.findByUserId(clientId).get();

        return aggregate.getStampsQuantity(businessId);
    }
}
