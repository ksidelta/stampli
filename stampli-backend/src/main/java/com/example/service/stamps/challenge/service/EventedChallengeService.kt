package com.example.service.stamps.challenge.service

import com.example.service.business.service.events.CreatedBusinessEvent
import org.springframework.context.event.EventListener
import org.springframework.jms.annotation.JmsListener

open class EventedChallengeService(challengeService: ChallengeService) : ChallengeService by challengeService {

    @EventListener(CreatedBusinessEvent::class)
    open fun consumeCreatedBusinessEvent(event: CreatedBusinessEvent) {
        this.createChallengeAggregate(event.ownerId, event.businessId);
    }
}
