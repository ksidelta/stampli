package com.example.service.authentication.user.repository;

import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;
import com.example.domain.context.authentication.user.repository.UserRepository;
import com.example.domain.context.authentication.user.repository.create.UserCreator;
import com.example.domain.context.authentication.user.repository.create.UserDuplicationException;
import com.example.domain.context.authentication.user.repository.find.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryFacade implements UserRepository {

    final protected UserCreator userCreator;
    final protected UserFinder userFinder;

    @Autowired
    public UserRepositoryFacade(UserCreator userCreator, UserFinder userFinder) {
        this.userCreator = userCreator;
        this.userFinder = userFinder;
    }


    @Override
    public AbstractUserAggregate findByUsernameAndPassword(String username, String password) throws BadCredentialsException {
        return userFinder.findByUsernameAndPassword(username, password);
    }

    @Override
    public AbstractUserAggregate findByIssuerAndUid(String issuer, Integer uid) throws BadCredentialsException {
        return userFinder.findByIssuerAndUid(issuer, uid);
    }

    @Override
    public void saveUser(AbstractUserAggregate userToSave) throws UserDuplicationException {
        userCreator.saveUser(userToSave);
    }
}
