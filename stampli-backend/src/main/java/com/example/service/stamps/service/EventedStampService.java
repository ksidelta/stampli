package com.example.service.stamps.service;

import com.example.domain.context.challenge.ChallengeClaimedEvent;
import com.example.service.authentication.register.UserRegisteredEvent;
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
        this.service.addStamp(challengeClaimedEvent.getClaimerId(), challengeClaimedEvent.getBusinessId());
    }

    @EventListener
    public void onRegisteredUser(UserRegisteredEvent userRegisteredEvent) {
        this.service.createStampsAggregateForClient(userRegisteredEvent.getUserId());
    }
}
