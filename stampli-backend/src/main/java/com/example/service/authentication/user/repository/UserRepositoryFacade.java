package com.example.service.authentication.user.repository;

import com.example.domain.authentication.user.entity.User;
import com.example.domain.authentication.user.repository.UserRepository;
import com.example.domain.authentication.user.repository.create.UserCreationDto;
import com.example.domain.authentication.user.repository.create.UserCreator;
import com.example.domain.authentication.user.repository.create.UserDuplicationException;
import com.example.domain.authentication.user.repository.find.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryFacade implements UserRepository {

    final protected UserCreator userCreator;
    final protected UserFinder userFinder;

    @Autowired
    public UserRepositoryFacade(UserCreator userCreator, UserFinder userFinder) {
        this.userCreator = userCreator;
        this.userFinder = userFinder;
    }

    @Override
    public User createUser(UserCreationDto userCreationDto) throws UserDuplicationException {
        return userCreator.createUser(userCreationDto);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws BadCredentialsException {
        return userFinder.findByUsernameAndPassword(username, password);
    }
}
