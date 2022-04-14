package com.example.controller.challenge;

import com.example.BaseTestConfiguration;
import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.modules.challenge.domain.ClaimProof;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.modules.challenge.controller.ChallengeController;
import com.example.modules.challenge.service.ChallengeService;
import com.example.modules.challenge.service.ChallengeTokenDTO;
import com.example.modules.challenge.service.ChallengingTokenDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class ChallengeControllerIntegration {
    protected MockMvc mockMvc;

    @Mock
    protected ChallengeService challengeService;

    @InjectMocks
    protected ChallengeController challengeController;

    @Autowired
    protected Filter springSecurityFilterChain;

    @Autowired
    TokenGenerator tokenGenerator;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(challengeController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    public void whenChallengeIsAcquiredThenItIsReturned() throws Exception {
        Mockito.when(challengeService.acquireToken(1, 2))
                .thenReturn(new ChallengeTokenDTO(1, 2, 3));

        final var ret = mockMvc.perform(
                post("/api/business/challenge/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + createToken())
        ).andExpect(status().is(201))
                .andExpect(jsonPath("$.issuerId", equalTo(1)))
                .andExpect(jsonPath("$.businessId", equalTo(2)))
                .andExpect(jsonPath("$.nonce", equalTo(3)));
    }

    @Test
    public void whenChallengeIsChallengedThenProofIsReturned() throws Exception {
        Mockito.when(challengeService.claimToken(new ChallengingTokenDTO(3, 2, 1, 4)))
                .thenReturn(new ClaimProof(1, 3, 2));

        final var ret = mockMvc.perform(
                post("/api/business/challenge/2/3/")
                        .content("{\"nonce\": 4}")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + createToken())
        ).andExpect(status().is(201))
                .andExpect(jsonPath("$.issuerId", equalTo(3)))
                .andExpect(jsonPath("$.businessId", equalTo(2)))
                .andExpect(jsonPath("$.claimerId", equalTo(1)));
    }

    public String createToken() {
        final var user = new AbstractUserAggregate();
        user.setRoles(Arrays.asList());
        user.setId(1);
        return tokenGenerator.createToken(user);
    }
}
