package com.example.modules.authentication.service.register;

import com.example.modules.authentication.domain.roles.UserRoles;
import com.example.modules.authentication.domain.user.entity.EmailPasswordUserAggregate;
import com.example.modules.authentication.domain.user.entity.TokenUserAggregate;
import com.example.modules.authentication.domain.user.entity.UserTokenDto;
import com.example.modules.authentication.domain.user.repository.UserRepository;
import com.example.modules.authentication.domain.user.repository.create.UserCreationDto;
import com.example.modules.authentication.domain.user.repository.create.UserDuplicationException;

import javax.transaction.Transactional;
import java.util.Arrays;

public class RegisterServiceImpl implements RegisterService {
    protected final UserRepository userRepository;

    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Integer registerUser(String email, String password) throws UserDuplicationException {
        var user = EmailPasswordUserAggregate.createUser(new UserCreationDto(email, password, Arrays.asList(UserRoles.USER.toString())));
        userRepository.saveUser(user);
        return user.getId();
    }

    @Override
    @Transactional
    public Integer registerUserWithToken(String issuer, String uid) throws UserDuplicationException {
        var user = TokenUserAggregate.createUser(new UserTokenDto(issuer, uid, Arrays.asList(UserRoles.USER.toString())));
        userRepository.saveUser(user);
        return user.getId();
    }
}
