package com.example.service.authentication.user.repository.create;

import com.example.BaseTestConfiguration;
import com.example.modules.authentication.domain.user.entity.EmailPasswordUserAggregate;
import com.example.modules.authentication.domain.user.repository.create.UserCreationDto;
import com.example.modules.authentication.domain.user.repository.create.UserCreator;
import com.example.modules.authentication.domain.user.repository.create.UserDuplicationException;
import com.example.modules.authentication.domain.user.repository.find.UserFinder;
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
        var user = new UserCreationDto(email, password, Arrays.asList("USER"));

        var savedUser = EmailPasswordUserAggregate.createUser(user);
        userCreator.saveUser(savedUser);
    }


}
