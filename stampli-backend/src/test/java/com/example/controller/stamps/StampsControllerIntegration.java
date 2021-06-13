package com.example.controller.stamps;

import com.example.BaseTestConfiguration;
import com.example.domain.context.authentication.user.entity.UserAggregate;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.service.stamps.service.StampService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class StampsControllerIntegration {
    protected MockMvc mockMvc;

    @Mock
    protected StampService stampService;

    @InjectMocks
    protected StampsController stampsController;

    @Autowired
    protected Filter springSecurityFilterChain;

    @Autowired
    TokenGenerator tokenGenerator;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(stampsController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    public void whenStampsInfoIsAskedThenCorrectInfoIsReturned() throws Exception {
        Mockito.when(stampService.getNumberOfStamps(1, 2)).thenReturn(3);

        final var ret = mockMvc.perform(
                get("/api/stamps/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + createToken())
        ).andExpect(status().is(200))
                .andExpect(jsonPath("$.quantityOfStamps", equalTo(3)));
    }

    public String createToken() {
        final var user = new UserAggregate();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }
}
