package com.example.service.authentication.register;

import com.example.domain.context.authentication.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RegisterConfiguration {
    @Bean
    @Primary
    public RegisterService registerService(
            @Qualifier RegisterService registerServiceImpl,
            ApplicationEventPublisher eventPublisher
    ) {
        return new EventedRegisterService(registerServiceImpl, eventPublisher);
    }

    @Bean
    @Qualifier
    public RegisterService registerServiceImpl(UserRepository userRepository) {
        return new RegisterServiceImpl(userRepository);
    }
}
