package com.example.infrastructure.domain.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

public class AbstractEventedAggregate {
    private transient final @Transient
    List<Object> domainEvents = new ArrayList<>();

    public void registerEvent(Object event) {
        domainEvents.add(event);
    }

    public void consumeEventsBy(ApplicationEventPublisher eventPublisher) {
        domainEvents.forEach(eventPublisher::publishEvent);
        domainEvents.clear();
    }

}
