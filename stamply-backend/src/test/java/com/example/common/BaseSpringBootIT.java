package com.example.common;

import com.example.SpringBoot;
import com.example.common.db.AbstractDatabaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

@SpringBootTest(classes = SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public abstract class BaseSpringBootIT extends AbstractDatabaseTest {
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
    static public void startMariaDBWithLoggingForClass(Class loggedClass) {
        mariadb.withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(loggedClass)));
        mariadb.start();
    }
}
