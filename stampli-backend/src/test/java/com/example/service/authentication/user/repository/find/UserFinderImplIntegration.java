package com.example.service.authentication.user.repository.find;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.domain.context.authentication.user.entity.EmailPasswordUserAggregate;
import com.example.domain.context.authentication.user.repository.create.UserCreationDto;
import com.example.domain.context.authentication.user.repository.create.UserCreator;
import com.example.domain.context.authentication.user.repository.find.UserFinder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class UserFinderImplIntegration extends AbstractDatabaseTest {
    @Qualifier("userFinder")
    @Autowired
    UserFinder userFinder;

    @Qualifier("userCreator")
    @Autowired
    UserCreator userCreator;

    @BeforeEach
    public void createUser() {
        var user = new UserCreationDto("user@is.existent", "password", Arrays.asList("USER"));

        var userToSave = EmailPasswordUserAggregate.createUser(user);

        userCreator.saveUser(userToSave);
    }

    @BeforeEach
    public void createOtherUser() {
        var user = new UserCreationDto("other@is.existent", "other-password", Arrays.asList("USER"));

        var userToSave = EmailPasswordUserAggregate.createUser(user);

        userCreator.saveUser(userToSave);
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
