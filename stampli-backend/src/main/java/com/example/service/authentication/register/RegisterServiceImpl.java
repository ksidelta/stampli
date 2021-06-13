package com.example.service.authentication.register;

import com.example.domain.context.authentication.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.context.authentication.roles.UserRoles;
import com.example.domain.context.authentication.user.entity.UserAggregate;
import com.example.domain.context.authentication.user.repository.UserRepository;
import com.example.domain.context.authentication.user.repository.create.UserCreationDto;
import com.example.domain.context.authentication.user.repository.create.UserDuplicationException;

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
        var user = UserAggregate.createUser(new UserCreationDto(email, Arrays.asList(UserRoles.USER.toString())));
        user.addPasswordAuthentication(new UserPasswordAuthenticationDto(password));
        userRepository.saveUser(user);
        return user.getId();
    }
}
