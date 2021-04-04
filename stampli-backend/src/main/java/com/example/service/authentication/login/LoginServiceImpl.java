package com.example.service.authentication.login;

import com.example.modules.authentication.user.UserImpl;
import com.example.modules.authentication.user.finder.UserFinder;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LoginServiceImpl implements LoginService {
    protected final UserFinder userFinder;

    LoginServiceImpl(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    /**
     * @return JWT token
     */
    public String login(String username, String password) {
        var user = this.userFinder.findByUsernameAndPassword(username, password);

        return user.createToken();
    }

    @Lookup
    public UserImpl userImpl() {
        return null;
    }
}
