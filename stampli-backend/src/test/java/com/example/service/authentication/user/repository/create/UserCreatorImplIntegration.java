package com.example.service.authentication.user.repository.create;

import com.example.BaseTestConfiguration;
import com.example.domain.context.authentication.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.context.authentication.user.entity.UserAggregate;
import com.example.domain.context.authentication.user.repository.create.UserCreationDto;
import com.example.domain.context.authentication.user.repository.create.UserCreator;
import com.example.domain.context.authentication.user.repository.create.UserDuplicationException;
import com.example.domain.context.authentication.user.repository.find.UserFinder;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
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

    @Qualifier("userFinder")
    @Autowired
    UserFinder userFinder;

    @Qualifier("userCreator")
    @Autowired
    UserCreator userCreator;

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

        var savedUser = UserAggregate.createUser(user);
        savedUser.addPasswordAuthentication(authenticationDto);
        userCreator.saveUser(savedUser);
    }


}
