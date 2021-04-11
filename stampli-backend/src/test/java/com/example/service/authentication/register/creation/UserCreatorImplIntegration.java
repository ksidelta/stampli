package com.example.service.authentication.register.creation;

import com.example.BaseTestConfiguration;
import com.example.domain.authentication.user.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.authentication.user.authenticator.UserPasswordAuthenticator;
import com.example.domain.authentication.user.creation.UserCreationDto;
import com.example.domain.authentication.user.creation.UserCreator;
import com.example.domain.authentication.user.creation.UserDuplicationException;
import com.example.domain.authentication.user.finder.UserFinder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class UserCreatorImplIntegration {
    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    UserFinder userFinder;

    @Autowired
    UserCreator userCreator;

    @Autowired
    UserPasswordAuthenticator userPasswordAuthenticator;

    @AfterEach
    public void removeUsers() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void whenUserIsCreatedThenSucceeds() {
        createUser("username", "password");
    }

    @Test
    public void whenTwoDifferentUsersWithSamePasswordIsCreatedThenSucceeds() {
        createUser("username", "password");
        createUser("username2", "password");
    }

    @Test
    public void whenTwoSameUsersWithSamePasswordIsCreatedThenSucceeds() {
        createUser("username", "password");
        Assertions.assertThrows(UserDuplicationException.class, () ->
                createUser("username", "password")
        );
    }

    private void createUser(String email, String password) {
        var user = new UserCreationDto(email, Arrays.asList("USER"));
        var authenticationDto = new UserPasswordAuthenticationDto(password);


        var savedUser = userCreator.createUser(user);
        userPasswordAuthenticator.addAuthentication(savedUser, authenticationDto);
    }


}
