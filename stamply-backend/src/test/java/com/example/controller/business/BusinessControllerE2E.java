package com.example.controller.business;

import com.example.SpringBoot;
import com.example.common.db.AbstractDatabaseTest;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.modules.business.controller.BusinessController;
import com.example.modules.business.domain.BusinessAggregate;
import com.example.modules.business.repository.BusinessRepository;
import com.example.modules.business.service.BusinessDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.Filter;
import java.util.Arrays;

import static com.example.common.BaseSpringBootIT.startMariaDBWithLoggingForClass;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
@ExtendWith(SpringExtension.class)
public class BusinessControllerE2E extends AbstractDatabaseTest {
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

    final HttpHeaders headers = new HttpHeaders();

    @BeforeAll
    static public void start() {
        startMariaDBWithLoggingForClass(BusinessControllerE2E.class);
    }

    @BeforeEach
    public void setUpHeaders() {
        headers.add("Authorization", "Bearer " + createToken());
    }

    @Test
    public void givenNonExistingBusinessWhenGotThenReturns404() {
        var response = restTemplate.exchange("/api/business/", HttpMethod.GET, new HttpEntity<>(headers), BusinessDto.class);

        assertThat(response.getStatusCode().value(), is(404));
    }

    @Test
    public void givenExistingBusinessWhenGotThenReturnsCorrect() {
        final var business = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));
        businessRepository.save(business);

        final var response = restTemplate.exchange("/api/business/", HttpMethod.GET, new HttpEntity<>(headers), BusinessDto.class);
        final var responseBody = response.getBody();

        assert responseBody != null;
        assertThat(response.getStatusCode().value(), is(200));
        assertThat(responseBody.getId(), is(business.getId()));
        assertThat(responseBody.getName(), equalTo(business.usingBusinessProfile().getBusinessName().getName()));
    }

    @Test
    public void givenNonExistingBusinessWhenCreatedThenReturnsCorrect() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + createToken());

        final var response = restTemplate.exchange("/api/business/", HttpMethod.POST, new HttpEntity<>(headers), BusinessDto.class);

        assertThat(response.getStatusCode().value(), is(201));
    }

    public String createToken() {
        final var user = new AbstractUserAggregate();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
