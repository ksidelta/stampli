package com.example.service.authentication.register;

import com.example.domain.authentication.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.authentication.roles.UserRoles;
import com.example.domain.authentication.user.entity.UserEntity;
import com.example.domain.authentication.user.repository.UserRepository;
import com.example.domain.authentication.user.repository.create.UserCreationDto;
import com.example.domain.authentication.user.repository.create.UserDuplicationException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class RegisterServiceImpl implements RegisterService {
    protected final UserRepository userRepository;

    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String email, String password) throws UserDuplicationException {
        var user = UserEntity.createUser(new UserCreationDto(email, Arrays.asList(UserRoles.USER.toString())));
        user.addPasswordAuthentication(new UserPasswordAuthenticationDto(password));
        userRepository.saveUser(user);
    }
}
