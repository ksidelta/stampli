package com.example.service.authentication.login.user.authenticator;

import com.example.domain.authentication.user.User;
import com.example.domain.authentication.user.UserAuthenticationPassword;
import com.example.domain.authentication.user.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.authentication.user.authenticator.UserPasswordAuthenticator;
import com.example.service.authentication.login.user.UserAuthenticationPasswordEntity;
import com.example.service.authentication.login.user.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserPasswordAuthenticatorImpl implements UserPasswordAuthenticator {
    final protected SessionFactory sessionFactory;

    public UserPasswordAuthenticatorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserAuthenticationPassword addAuthentication(User user, UserPasswordAuthenticationDto userPasswordAuthenticationDto) {
        var userEntity = sessionFactory.getCurrentSession().get(UserEntity.class, user.getId());

        var authentication = new UserAuthenticationPasswordEntity();
        authentication.setPassword(userPasswordAuthenticationDto.getPassword());
        authentication.setUserId(userEntity.getId());
        authentication.setUser(userEntity);

        sessionFactory.getCurrentSession().save(authentication);

        return authentication;
    }
}
