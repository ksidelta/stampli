package com.example.service.challenge.service

import com.example.modules.business.service.events.CreatedBusinessEvent
import org.springframework.context.event.EventListener

open class EventedChallengeService(challengeService: ChallengeService) : ChallengeService by challengeService {

    @EventListener(CreatedBusinessEvent::class)
    open fun consumeCreatedBusinessEvent(event: CreatedBusinessEvent) {
        this.createChallengeAggregate(event.ownerId, event.businessId);
    }
}
