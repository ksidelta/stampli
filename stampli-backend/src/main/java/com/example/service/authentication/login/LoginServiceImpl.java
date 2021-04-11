package com.example.service.authentication.login;

import com.example.domain.authentication.token.TokenGenerator;
import com.example.domain.authentication.user.entity.UserImpl;
import com.example.domain.authentication.user.repository.find.UserFinder;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {
    protected final UserFinder userFinder;
    protected final TokenGenerator tokenGenerator;

    LoginServiceImpl(UserFinder userFinder, TokenGenerator tokenGenerator) {
        this.userFinder = userFinder;
        this.tokenGenerator = tokenGenerator;
    }

    /**
     * @return JWT token
     */
    public String login(String username, String password) {
        var user = this.userFinder.findByUsernameAndPassword(username, password);
        return tokenGenerator.createToken(user);
    }

    @Lookup
    public UserImpl userImpl() {
        return null;
    }
}
