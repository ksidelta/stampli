package com.example.service.authentication.login;

import com.auth0.jwt.JWT;
import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.domain.context.authentication.user.repository.UserRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class LoginServiceTokenIntegration extends AbstractDatabaseTest {
    final String ISSUER = "accounts.google.com";
    final String OTHER_ISSUER = "accounts.fb.com";

    final Integer UID = 1;

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void givenNoCreatedUserThenCreatedAndTokenReturnedOnLogin() {
        var token = loginService.loginOrRegister(ISSUER, UID);
        final var id = userRepository.findByIssuerAndUid(ISSUER, UID).getId();

        final var decodedToken = JWT.decode(token);
        MatcherAssert.assertThat(decodedToken.getSubject(), equalTo("" + id));
    }

    @Test
    public void givenCreatedUserThenTokenReturnedOnLogin() {
        loginService.loginOrRegister(ISSUER, UID);
        final var id = userRepository.findByIssuerAndUid(ISSUER, UID).getId();

        var token = loginService.loginOrRegister(ISSUER, UID);

        final var decodedToken = JWT.decode(token);
        MatcherAssert.assertThat(decodedToken.getSubject(), equalTo("" + id));
    }

    @Test
    public void givenCreatedUserWhenOtherUserQueriedThenTokenReturnedOnLoginIsCorrect() {
        loginService.loginOrRegister(OTHER_ISSUER, UID);


        var token = loginService.loginOrRegister(ISSUER, UID);
        final var id = userRepository.findByIssuerAndUid(ISSUER, UID).getId();

        final var decodedToken = JWT.decode(token);
        MatcherAssert.assertThat(decodedToken.getSubject(), equalTo("" + id));
    }
}
