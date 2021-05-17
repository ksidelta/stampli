package com.example.service.authentication.login;

import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.domain.authentication.user.repository.UserRepository;
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
    public String login(String email, String password) {
        var user = this.userRepository.findByUsernameAndPassword(email, password);
        return tokenGenerator.createToken(user);
    }
}
