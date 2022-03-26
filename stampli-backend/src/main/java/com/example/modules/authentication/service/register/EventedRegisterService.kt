package com.example.modules.authentication.service.register

import org.springframework.context.ApplicationEventPublisher
import javax.transaction.Transactional

open class EventedRegisterService(val registerService: RegisterService, val eventPublisher: ApplicationEventPublisher) :
    RegisterService by registerService {

    @Transactional //
    override fun registerUser(email: String?, password: String?): Int {
        val userId = registerService.registerUser(email, password)

        eventPublisher.publishEvent(UserRegisteredEvent(userId));

        return userId;
    }

    override fun registerUserWithToken(issuer: String?, id: String?): Int {
        val userId = registerService.registerUserWithToken(issuer, id)

        eventPublisher.publishEvent(UserRegisteredEvent(userId));

        return userId;
    }
}
