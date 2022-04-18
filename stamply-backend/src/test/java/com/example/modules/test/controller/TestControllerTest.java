package com.example.modules.test.controller;

import com.example.SpringBoot;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

@SpringBootTest(classes = SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Disabled
class TestControllerTest {
    static MariaDBContainer mariadb = new MariaDBContainer();

    @DynamicPropertySource
    static public void provideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("db.mysql.db", () -> mariadb.getDatabaseName());
        dynamicPropertyRegistry.add("db.mysql.host", () -> mariadb.getHost());
        dynamicPropertyRegistry.add("db.mysql.port", () -> mariadb.getFirstMappedPort());
        dynamicPropertyRegistry.add("db.mysql.username", () -> mariadb.getUsername());
        dynamicPropertyRegistry.add("db.mysql.password", () -> mariadb.getPassword());
    }

    @BeforeAll
    static public void start() {
        mariadb.withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(TestControllerTest.class)));
        mariadb.start();
    }

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void test() {
        var response = restTemplate.getForObject("/api/test/health", String.class);

        MatcherAssert.assertThat(response, Matchers.equalTo("ok"));
    }

    @Test
    public void testFail() {
        var response = restTemplate.getForEntity("/api/test/forbidden", String.class);

        MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(HttpStatus.UNAUTHORIZED));
    }
}