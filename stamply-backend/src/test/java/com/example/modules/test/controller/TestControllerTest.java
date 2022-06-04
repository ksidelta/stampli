package com.example.modules.test.controller;

import com.example.SpringBoot;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.common.BaseSpringBootIT.startMariaDBWithLoggingForClass;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(classes = SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Disabled
class TestControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        startMariaDBWithLoggingForClass(TestControllerTest.class);
    }

    @Test
    public void test() {
        var response = restTemplate.getForObject("/api/test/health", String.class);

        assertThat(response, Matchers.equalTo("ok"));
    }

    @Test
    public void testFail() {
        var response = restTemplate.getForEntity("/api/test/forbidden", String.class);

        assertThat(response.getStatusCode(), Matchers.equalTo(HttpStatus.UNAUTHORIZED));
    }
}