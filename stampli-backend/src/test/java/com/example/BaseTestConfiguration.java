package com.example;

import com.example.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfiguration.class, AppConfig.class})
public class BaseTestConfiguration {
}
