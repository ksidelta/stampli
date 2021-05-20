package com.example.controller.authentication.business;

import com.example.BaseTestConfiguration;
import com.example.controller.business.BusinessController;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.domain.context.authentication.user.entity.UserEntity;
import com.example.service.business.service.BusinessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class BusinessControllerAuthenticationIntegration {
    protected MockMvc mockMvc;

    @Mock
    protected BusinessService businessService;

    @InjectMocks
    protected BusinessController businessController;

    @Autowired
    protected TokenGenerator tokenGenerator;

    @Autowired
    protected Filter springSecurityFilterChain;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(businessController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    public void givenExistingUserWhenBusinessIsCreatedThenServiceIsExecutedWithUserId() throws Exception {
        final var mvcResult = mockMvc.perform(
                post("/api/business/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + createToken())
        ).andExpect(status().is(201)).andReturn();

        verify(businessService).createBusiness(1);
    }

    @Test
    public void givenUnauthenticatedUserWhenBusinessIsCreatedThen401IsReturned() throws Exception {
        final var mvcResult = mockMvc.perform(
                post("/api/business/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is(401)).andReturn();
    }

    public String createToken() {
        final var user = new UserEntity();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }

}
