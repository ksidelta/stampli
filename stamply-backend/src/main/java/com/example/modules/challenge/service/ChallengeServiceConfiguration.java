package com.example.modules.challenge.service;

import com.example.modules.challenge.domain.ChallengeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.security.SecureRandom;

@Configuration
public class ChallengeServiceConfiguration {
    @Bean
    @Primary
    public ChallengeService challengeService(@Qualifier ChallengeService websocketChallengeService) {
        return new EventedChallengeService(websocketChallengeService);
    }

    @Bean
    @Qualifier
    public ChallengeService websocketChallengeService(@Qualifier ChallengeService challengeServiceImpl, SimpMessagingTemplate simpMessagingTemplate) {
        return new WebsocketChallengeService(challengeServiceImpl, simpMessagingTemplate);
    }

    @Bean
    @Qualifier
    public ChallengeService challengeServiceImpl(ChallengeRepository challengeRepository, SecureRandom secureRandom) {
        return new ChallengeServiceImpl(challengeRepository, secureRandom);
    }
}
