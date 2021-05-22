package com.example.service.stamps.challenge.service;

import com.example.domain.context.challenge.ChallengeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class ChallengeConfiguration {

    @Bean
    public ChallengeService challengeService(ChallengeRepository challengeRepository, SecureRandom secureRandom) {
        return new EventedChallengeService(new ChallengeServiceImpl(challengeRepository, secureRandom));
    }
}
