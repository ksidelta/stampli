package com.example.modules.authentication.service.login;

import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.infrastructure.jwt.generator.TokenGenerator;
import com.example.modules.authentication.domain.user.repository.UserRepository;
import com.example.modules.authentication.service.register.RegisterService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class LoginServiceImpl implements LoginService {
    protected final UserRepository userRepository;
    protected final TokenGenerator tokenGenerator;
    protected final RegisterService registerService;

    LoginServiceImpl(UserRepository userRepository, TokenGenerator tokenGenerator, RegisterService registerService) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.registerService = registerService;
    }

    /**
     * @return JWT token
     */
    @Transactional
    public String login(String email, String password) {
        var user = this.userRepository.findByUsernameAndPassword(email, password);
        return tokenGenerator.createToken(user);
    }

    /**
     * @param issuer JWT issuer like accounts.google.com
     * @param uid    id of user for this issuer
     * @return JWT token
     */
    @Override
    public String loginOrRegister(String issuer, String uid) {
        AbstractUserAggregate user;

        try {
            user = this.userRepository.findByIssuerAndUid(issuer, uid);
        } catch (BadCredentialsException ex) {
            registerService.registerUserWithToken(issuer, uid);
            user = this.userRepository.findByIssuerAndUid(issuer, uid);
        }
        return tokenGenerator.createToken(user);
    }
}
