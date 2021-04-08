package com.example.controller.authentication.login;

import com.auth0.jwt.JWT;
import com.example.domain.authentication.AuthenticationConfiguration;
import com.example.domain.authentication.token.sign.AlgorithmHolder;
import com.example.domain.authentication.user.User;
import com.example.domain.authentication.user.UserImpl;
import com.example.domain.authentication.user.finder.UserFinder;
import com.example.service.authentication.login.LoginServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({LoginControllerTest.LocalTestConfiguration.class})
@ActiveProfiles({"com.example.controller.authentication.login.LoginController"})
public class LoginControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected LoginController loginController;

    @Autowired
    protected AlgorithmHolder algorithmHolder;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(loginController).build();
    }

    @Test
    public void incorrectLoginReturns404() throws Exception {
        mockMvc.perform(
                post("/api/login/basic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"incorrect\", \"password\": \"incorrect\"}")
        ).andExpect(status().is(404));
    }

    @Test
    public void correctLoginReturnsToken() throws Exception {
        final var mvcResult = mockMvc.perform(
                post("/api/login/basic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"username\", \"password\": \"password\"}")
        ).andExpect(status().is(200)).andReturn();

        final var token = mvcResult.getResponse().getHeader("Set-Token");
        final var decodedToken = JWT.decode(token);


        assertThat(decodedToken.getClaim("roles").asList(String.class), contains("USER"));
        assertThat(decodedToken.getSubject(), Matchers.equalTo("2137"));
        algorithmHolder.getAlgorithm().verify(decodedToken);
    }

    @Import({LoginController.class, AuthenticationConfiguration.class, UserImpl.class, LoginServiceImpl.class})
    @Profile({"com.example.controller.authentication.login.LoginController"})
    static class LocalTestConfiguration {
        @Bean
        UserFinder userFinder(ApplicationContext applicationContext) {
            return new UserFinder() {
                @Override
                public User findByUsernameAndPassword(String username, String password) {
                    if (username.equals("username") && password.equals("password")) {
                        var user = userImpl();
                        user.setId(2137);
                        user.setRoles(Arrays.asList("USER"));

                        return user;
                    }
                    throw new BadCredentialsException("");
                }

                @Lookup
                UserImpl userImpl() {
                    return applicationContext.getBean(UserImpl.class);
                }
            };
        }
    }

}

