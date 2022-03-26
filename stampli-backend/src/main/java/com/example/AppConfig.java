package com.example;

import com.example.bootstrap.database.DatabaseConfiguration;
import com.example.bootstrap.security.WebSecurityConfig;
import com.example.infrastructure.InfrastructureConfiguration;
import com.example.modules.ModulesConfiguration;
import com.example.translation.AnticorruptionLayerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import({
        WebSecurityConfig.class,
        DatabaseConfiguration.class,
        AnticorruptionLayerConfiguration.class,
        InfrastructureConfiguration.class,
        ModulesConfiguration.class
})
@EnableWebSecurity
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableRetry
public class AppConfig {
}
