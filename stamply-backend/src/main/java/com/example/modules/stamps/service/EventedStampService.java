package com.example.modules.stamps.service;

import com.example.modules.challenge.domain.ChallengeClaimedEvent;
import com.example.modules.authentication.service.register.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventedStampService {
    final StampService service;

    public EventedStampService(StampService service) {
        this.service = service;
    }

    @EventListener
    public void onChallengeClaim(ChallengeClaimedEvent challengeClaimedEvent) {
        this.service.addStamp(challengeClaimedEvent.claimerId(), challengeClaimedEvent.businessId());
    }

    @EventListener
    public void onRegisteredUser(UserRegisteredEvent userRegisteredEvent) {
        this.service.createStampsAggregateForClient(userRegisteredEvent.userId());
    }
}
