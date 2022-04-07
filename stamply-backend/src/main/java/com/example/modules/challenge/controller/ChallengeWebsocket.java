package com.example.modules.challenge.controller;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChallengeWebsocket {
    JmsTemplate jmsTemplate;

    public ChallengeWebsocket(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @SubscribeMapping("/business/challenge/{businessId}/{issuerId}")
    public void claimTopic(@DestinationVariable Integer businessId, @DestinationVariable Integer issuerId) {
    }
}
