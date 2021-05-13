package com.example;

import com.example.bootstrap.database.DatabaseConfiguration;
import com.example.bootstrap.security.WebSecurityConfig;
import com.example.controller.ControllerConfiguration;
import com.example.service.ServiceConfiguration;
import com.example.translation.AnticorruptionLayerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.domain")
@Import({
        WebSecurityConfig.class,
        DatabaseConfiguration.class,
        ControllerConfiguration.class,
        ServiceConfiguration.class,
        AnticorruptionLayerConfiguration.class
})
@EnableRetry
public class AppConfig {
}
