package com.example.controller.stamps.challenge;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Controller
public class ChallengeWebsocket {
    JmsTemplate jmsTemplate;

    public ChallengeWebsocket(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @SubscribeMapping("/api/business/challenge/{businessId}/{issuerId}")
    public String claimTopic(@DestinationVariable Integer businessId, @DestinationVariable Integer issuerId) {
        final String ksi = "/api/business/challenge/" + businessId + "/" + issuerId;
        return ksi;
    }
}
