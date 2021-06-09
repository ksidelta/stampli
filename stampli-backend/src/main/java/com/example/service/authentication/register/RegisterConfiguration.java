package com.example.service.authentication.register;

import com.example.domain.context.authentication.user.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterConfiguration {
    @Bean
    public RegisterService registerService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        return new EventedRegisterService(new RegisterServiceImpl(userRepository), eventPublisher);
    }
}
