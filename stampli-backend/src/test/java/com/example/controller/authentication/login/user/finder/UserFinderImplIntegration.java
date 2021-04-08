package com.example.controller.authentication.login.user.finder;

import com.example.BaseTestConfiguration;
import com.example.domain.authentication.user.User;
import com.example.domain.authentication.user.UserAuthenticationPassword;
import com.example.domain.authentication.user.authenticator.UserPasswordAuthenticator;
import com.example.domain.authentication.user.creation.UserCreator;
import com.example.domain.authentication.user.finder.UserFinder;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserFinderImplIntegration {

    @Autowired
    UserFinder userFinder;

    @Autowired
    UserCreator userCreator;

    @Autowired
    UserPasswordAuthenticator userPasswordAuthenticator;

    @BeforeAll
    public void createUser() {
        var user = new User() {
            @Override
            public Integer getId() {
                return null;
            }

            @Override
            public List<String> getRoles() {
                return Arrays.asList("USER");
            }
        };

        var authenticator = new UserAuthenticationPassword() {
            @Override
            public String getPassword() {
                return "password";
            }

            @Override
            public User getUser() {
                return null;
            }
        };


        var savedUser = userCreator.createUser(user);
        userPasswordAuthenticator.addAuthentication(savedUser, authenticator);
    }

    @Test
    public void whenUserIsNotFoundThenExceptionIsThrown() {
        Assertions.assertThrows(BadCredentialsException.class, () ->
                userFinder.findByUsernameAndPassword("user@not.found", "nothing")
        );
    }

    @Test
    public void whenUserHasWrongPasswordThenExceptionIsThrown() {
        Assertions.assertThrows(BadCredentialsException.class, () ->
                userFinder.findByUsernameAndPassword("user@user.com", "wrong-password")
        );
    }

    @Test
    public void whenUserIsFoundThenUserIsReturned() {
        var user = userFinder.findByUsernameAndPassword("user@user.com", "password");

        assertThat(user.getRoles(), hasItems("USER"));
    }
}
