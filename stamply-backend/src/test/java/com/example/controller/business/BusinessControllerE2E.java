package com.example.controller.business;

import com.example.SpringBoot;
import com.example.common.db.AbstractDatabaseTest;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.modules.business.controller.BusinessController;
import com.example.modules.business.domain.BusinessAggregate;
import com.example.modules.business.repository.BusinessRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import javax.servlet.Filter;
import java.util.Arrays;

@SpringBootTest(classes = SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
@ExtendWith(SpringExtension.class)
public class BusinessControllerE2E extends AbstractDatabaseTest {
    static MariaDBContainer mariadb = new MariaDBContainer();

    @DynamicPropertySource
    static public void provideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("db.mysql.db", () -> mariadb.getDatabaseName());
        dynamicPropertyRegistry.add("db.mysql.host", () -> mariadb.getHost());
        dynamicPropertyRegistry.add("db.mysql.port", () -> mariadb.getFirstMappedPort());
        dynamicPropertyRegistry.add("db.mysql.username", () -> mariadb.getUsername());
        dynamicPropertyRegistry.add("db.mysql.password", () -> mariadb.getPassword());
    }

    @Autowired
    protected BusinessController businessController;

    @Autowired
    protected TokenGenerator tokenGenerator;

    @Autowired
    protected Filter springSecurityFilterChain;

    @Autowired
    protected BusinessRepository businessRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static public void start() {
        mariadb.withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(BusinessControllerE2E.class)));
        mariadb.start();
    }

    @Test
    public void test() {
        final var business = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));
        businessRepository.save(business);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + createToken());
//        headers.add("Accept", "application/json");
//        headers.add("Content-type", "application/json");

        var response = restTemplate.exchange("/api/business/", HttpMethod.GET, new HttpEntity<>(headers), BusinessAggregate.class);

        System.out.println("asd");
    }

//    @Test
//    public void givenNonExistingBusinessWhenGotThenReturns404() throws Exception {
//        final var mvcResult = mockMvc.perform(
//                get("/api/business/")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + createToken())
//        ).andExpect(status().is(404)).andReturn();
//    }
//
//    @Test
//    public void givenExistingBusinessWhenGotThenReturnsCorrect() throws Exception {
//        final var business = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));
//        businessRepository.save(business);
//
//        mockMvc.perform(
//                get("/api/business/")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + createToken())
//        ).andExpect(status().is(200))
//                .andExpect(jsonPath("$.id", equalTo(business.getId())))
//                .andExpect(jsonPath("$.name", equalTo(business.usingBusinessProfile().getBusinessName().getName())))
//                .andReturn();
//    }
//
//    @Test
//    public void givenNonExistingBusinessWhenCreatedThenReturnsCorrect() throws Exception {
//        mockMvc.perform(
//                post("/api/business/")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + createToken())
//        ).andExpect(status().is(201)).andReturn();
//    }

    public String createToken() {
        final var user = new AbstractUserAggregate();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
