package com.example.service.stamps.service;

public interface StampService {
    void createStampsAggregateForClient(Integer clientId);

    void addStamp(Integer clientId, Integer businessId);

    Integer getNumberOfStamps(Integer clientId, Integer businessId);
}
