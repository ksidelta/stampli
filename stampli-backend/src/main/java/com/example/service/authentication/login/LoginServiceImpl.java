package com.example.service.authentication.login;

import com.example.domain.authentication.token.TokenGenerator;
import com.example.domain.authentication.user.entity.UserImpl;
import com.example.domain.authentication.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {
    protected final UserRepository userRepository;
    protected final TokenGenerator tokenGenerator;

    LoginServiceImpl(UserRepository userRepository, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    /**
     * @return JWT token
     */
    public String login(String username, String password) {
        var user = this.userRepository.findByUsernameAndPassword(username, password);
        return tokenGenerator.createToken(user);
    }

    @Lookup
    public UserImpl userImpl() {
        return null;
    }
}
