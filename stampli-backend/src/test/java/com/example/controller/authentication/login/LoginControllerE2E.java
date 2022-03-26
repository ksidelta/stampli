package com.example.controller.authentication.login;

import com.auth0.jwt.JWT;
import com.example.BaseTestConfiguration;
import com.example.modules.authentication.controller.login.LoginController;
import com.example.modules.authentication.domain.user.entity.EmailPasswordUserAggregate;
import com.example.modules.authentication.domain.user.repository.UserRepository;
import com.example.modules.authentication.domain.user.repository.create.UserCreationDto;
import com.example.infrastructure.jwt.sign.AlgorithmHolder;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class LoginControllerE2E {
    protected MockMvc mockMvc;

    @Autowired
    protected LoginController loginController;

    @Autowired
    protected AlgorithmHolder algorithmHolder;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected Filter springSecurityFilterChain;


    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(loginController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    Integer userId;

    @BeforeEach
    public void createUser() {
        var user = new UserCreationDto("user@is.existent", "password", Arrays.asList("USER"));

        var savedUser = EmailPasswordUserAggregate.createUser(user);

        userRepository.saveUser(savedUser);
        userId = savedUser.getId();
    }

    @AfterEach
    public void removeUsers() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void incorrectLoginReturns404() throws Exception {
        mockMvc.perform(
                post("/api/authentication/login/basic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"incorrect\", \"password\": \"incorrect\"}")
        ).andExpect(status().is(404));
    }

    @Test
    public void correctLoginReturnsToken() throws Exception {
        final var mvcResult = mockMvc.perform(
                post("/api/authentication/login/basic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@is.existent\", \"password\": \"password\"}")
        ).andExpect(status().is(200)).andReturn();

        final var token = mvcResult.getResponse().getHeader("Set-Token");
        final var decodedToken = JWT.decode(token);

        assertThat(decodedToken.getClaim("roles").asList(String.class), contains("USER"));
        assertThat(decodedToken.getSubject(), Matchers.equalTo(userId.toString()));
        algorithmHolder.getAlgorithm().verify(decodedToken);
    }
}

