package com.example;

import com.example.bootstrap.database.DatabaseConfiguration;
import com.example.bootstrap.security.WebSecurityConfig;
import com.example.controller.ControllerConfiguration;
import com.example.service.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.domain")
@Import({
        WebSecurityConfig.class,
        DatabaseConfiguration.class,
        ControllerConfiguration.class,
        ServiceConfiguration.class
})
@EnableRetry
public class AppConfig {
}
