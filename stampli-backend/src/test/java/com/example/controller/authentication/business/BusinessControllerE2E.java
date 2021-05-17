package com.example.controller.authentication.business;

import com.example.BaseTestConfiguration;
import com.example.controller.business.BusinessController;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.domain.authentication.user.entity.UserEntity;
import com.example.domain.business.entity.business.BusinessAggregate;
import com.example.domain.business.entity.owner.Owner;
import com.example.domain.business.repository.BusinessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class BusinessControllerE2E {
    protected MockMvc mockMvc;

    @Autowired
    protected BusinessController businessController;

    @Autowired
    protected TokenGenerator tokenGenerator;

    @Autowired
    protected Filter springSecurityFilterChain;

    @Autowired
    protected BusinessRepository businessRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(businessController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    public void givenNonExistingBusinessWhenGotThenReturns404() throws Exception {
        final var mvcResult = mockMvc.perform(
                get("/api/business/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + createToken())
        ).andExpect(status().is(404)).andReturn();
    }

    @Test
    public void givenExistingBusinessWhenGotThenReturnsCorrect() throws Exception {
        final var business = BusinessAggregate.createBusinessAggregate(new Owner(1));
        businessRepository.save(business);

        try {
            mockMvc.perform(
                    get("/api/business/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer " + createToken())
            ).andExpect(status().is(200))
                    .andExpect(jsonPath("$.id", equalTo(business.getId())))
                    .andExpect(jsonPath("$.name", equalTo(business.usingBusinessProfile().getBusinessName().getName())))
                    .andReturn();

        } finally {
            businessRepository.remove(business);
        }
    }

    @Test
    public void givenNonExistingBusinessWhenCreatedThenReturnsCorrect() throws Exception {
        try {
            mockMvc.perform(
                    post("/api/business/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer " + createToken())
            ).andExpect(status().is(201)).andReturn();

        } finally {
            businessRepository.remove(businessRepository.findByOwnerId(1).get());
        }
    }

    public String createToken() {
        final var user = new UserEntity();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
