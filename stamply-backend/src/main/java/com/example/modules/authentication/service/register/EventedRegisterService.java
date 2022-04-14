package com.example.modules.authentication.service.register;

import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;


public record EventedRegisterService(RegisterService registerService,
                                     ApplicationEventPublisher eventPublisher) implements RegisterService {

    @Transactional
    public Integer registerUser(String email, String password) {
        var userId = registerService.registerUser(email, password);

        eventPublisher.publishEvent(new UserRegisteredEvent(userId));

        return userId;
    }

    public Integer registerUserWithToken(String issuer, String id) {
        var userId = registerService.registerUserWithToken(issuer, id);

        eventPublisher.publishEvent(new UserRegisteredEvent(userId));

        return userId;
    }
}
