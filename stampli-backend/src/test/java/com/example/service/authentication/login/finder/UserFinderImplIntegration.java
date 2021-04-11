package com.example.service.authentication.login.finder;

import com.example.BaseTestConfiguration;
import com.example.domain.authentication.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.authentication.authenticator.UserPasswordAuthenticator;
import com.example.domain.authentication.user.repository.creation.UserCreationDto;
import com.example.domain.authentication.user.repository.creation.UserCreator;
import com.example.domain.authentication.user.repository.find.UserFinder;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class UserFinderImplIntegration {
    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    UserFinder userFinder;

    @Autowired
    UserCreator userCreator;

    @BeforeEach
    public void createUser() {
        var user = new UserCreationDto("user@is.existent", Arrays.asList("USER"));
        var authenticationDto = new UserPasswordAuthenticationDto("password");


        var savedUser = userCreator.createUser(user);
        savedUser.addPasswordAuthentication(authenticationDto);
    }

    @BeforeEach
    public void createOtherUser() {
        var user = new UserCreationDto("other@is.existent", Arrays.asList("USER"));
        var authenticationDto = new UserPasswordAuthenticationDto("other-password");


        var savedUser = userCreator.createUser(user);
        savedUser.addPasswordAuthentication(authenticationDto);
    }

    @AfterEach
    public void removeUsers() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void whenUserIsNotFoundThenExceptionIsThrown() {
        Assertions.assertThrows(BadCredentialsException.class, () ->
                userFinder.findByUsernameAndPassword("user@non.existent", "nothing")
        );
    }

    @Test
    public void whenUserHasWrongPasswordThenExceptionIsThrown() {
        Assertions.assertThrows(BadCredentialsException.class, () ->
                userFinder.findByUsernameAndPassword("user@is.existent", "wrong-password")
        );
    }

    @Test
    public void whenUserIsSearchedWithWrongPasswordThenExceptionIsThrown() {
        Assertions.assertThrows(BadCredentialsException.class, () ->
                userFinder.findByUsernameAndPassword("user@not.found", "password")
        );
    }

    @Test
    public void whenUserIsFoundThenUserIsReturned() {
        var user = userFinder.findByUsernameAndPassword("user@is.existent", "password");

        assertThat(user.getRoles(), hasItems("USER"));
    }


}