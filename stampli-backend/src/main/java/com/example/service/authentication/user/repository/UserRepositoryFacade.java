package com.example.service.authentication.user.repository;

import com.example.domain.authentication.user.entity.UserEntity;
import com.example.domain.authentication.user.repository.UserRepository;
import com.example.domain.authentication.user.repository.create.UserCreator;
import com.example.domain.authentication.user.repository.create.UserDuplicationException;
import com.example.domain.authentication.user.repository.find.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserEntity findByUsernameAndPassword(String username, String password) throws BadCredentialsException {
        return userFinder.findByUsernameAndPassword(username, password);
    }

    @Override
    public void saveUser(UserEntity userToSave) throws UserDuplicationException {
        userCreator.saveUser(userToSave);
    }
}
