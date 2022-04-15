package com.example.modules.test.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@Disabled
class TestControllerTest {
    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

    @Test
    public void test() {
    }
}