package com.example.service.stamps.challenge.service;

import com.example.domain.context.challenge.ChallengeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.security.SecureRandom;

@Configuration
public class ChallengeConfiguration {

    @Bean
    public ChallengeService challengeService(
            ChallengeRepository challengeRepository,
            SecureRandom secureRandom,
            SimpMessagingTemplate simpMessagingTemplate
    ) {
        return

                new EventedChallengeService(
                        new WebsocketChallengeService(
                                new ChallengeServiceImpl(challengeRepository, secureRandom),
                                simpMessagingTemplate
                        )
                );
    }
}
