package com.example.controller.authentication.register;

import com.example.BaseTestConfiguration;
import com.example.infrastructure.jwt.sign.AlgorithmHolder;
import com.example.modules.authentication.controller.register.RegisterController;
import com.example.modules.authentication.domain.user.entity.EmailPasswordUserAggregate;
import com.example.modules.authentication.domain.user.repository.UserRepository;
import com.example.modules.authentication.domain.user.repository.create.UserCreationDto;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class RegisterControllerE2E {
    protected MockMvc mockMvc;

    @Autowired
    protected RegisterController registerController;

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
        mockMvc = standaloneSetup(registerController)
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
    public void correctRegisterReturns201() throws Exception {
        mockMvc.perform(
                post("/api/authentication/register/basic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"new@user.registered\", \"password\": \"password\"}")
        ).andExpect(status().is(201));
    }

    @Test
    public void duplicatedRegisterReturns409() throws Exception {
        mockMvc.perform(
                post("/api/authentication/register/basic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"user@is.existent\", \"password\": \"password\"}")
        ).andExpect(status().is(409));
    }
}

