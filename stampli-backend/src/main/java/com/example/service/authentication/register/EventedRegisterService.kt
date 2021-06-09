package com.example.service.authentication.register

import org.springframework.context.ApplicationEventPublisher

class EventedRegisterService(val registerService: RegisterService, val eventPublisher: ApplicationEventPublisher) :
    RegisterService by registerService {

    override fun registerUser(email: String?, password: String?): Int {
        val userId = registerService.registerUser(email, password)

        eventPublisher.publishEvent(UserRegisteredEvent(userId));

        return userId;
    }
}
