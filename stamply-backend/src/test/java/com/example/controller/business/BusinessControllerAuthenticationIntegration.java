package com.example.controller.business;

import com.example.SpringBoot;
import com.example.common.BaseSpringBootIT;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.modules.business.controller.BusinessController;
import com.example.modules.business.service.BusinessDto;
import com.example.modules.business.service.BusinessService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
@ExtendWith(SpringExtension.class)
public class BusinessControllerAuthenticationIntegration extends BaseSpringBootIT {
    @Mock
    protected BusinessService businessService;

    @InjectMocks
    protected BusinessController businessController;

    @Autowired
    protected TokenGenerator tokenGenerator;

    @Autowired
    protected Filter springSecurityFilterChain;

    @Autowired
    TestRestTemplate restTemplate;

    final HttpHeaders headers = new HttpHeaders();

    @BeforeAll
    static public void start() {
        startMariaDBWithLoggingForClass(BusinessControllerAuthenticationIntegration.class);
    }

    @BeforeEach
    public void setUpHeaders() {
        headers.add("Authorization", "Bearer " + createToken());
    }

    @Test
    public void givenExistingUserWhenBusinessIsCreatedThenServiceIsExecutedWithUserId() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + createToken());

        final var response = restTemplate.exchange("/api/business", HttpMethod.POST, new HttpEntity<>(headers), BusinessDto.class);

        assertThat(response.getStatusCode().value(), is(201));
        verify(businessService).createBusiness(1);
    }

    @Test
    public void givenUnauthenticatedUserWhenBusinessIsCreatedThen401IsReturned() {
        final var response = restTemplate.exchange("/api/business", HttpMethod.POST, new HttpEntity<>(new HttpHeaders()), BusinessDto.class);

        assertThat(response.getStatusCode().value(), is(401));
    }

        public String createToken() {
        final var user = new AbstractUserAggregate();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
