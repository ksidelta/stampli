package com.example.service.stamps.challenge.service

import com.example.service.business.service.events.CreatedBusinessEvent
import org.springframework.context.event.EventListener

class EventedChallengeService(challengeService: ChallengeService) : ChallengeService by challengeService {
    @EventListener(CreatedBusinessEvent::class)
    fun consumeCreatedBusinessEvent(event: CreatedBusinessEvent) {
        this.createChallengeAggregate(event.ownerId, event.businessId);
    }
}
