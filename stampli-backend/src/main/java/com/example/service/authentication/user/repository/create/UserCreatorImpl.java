package com.example.service.authentication.user.repository.create;

import com.example.domain.authentication.user.entity.User;
import com.example.domain.authentication.user.repository.create.UserCreationDto;
import com.example.domain.authentication.user.repository.create.UserCreator;
import com.example.domain.authentication.user.repository.create.UserDuplicationException;
import com.example.service.authentication.user.entity.UserEntity;
import com.example.service.authentication.roles.UserRoleEntity;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Repository
@Transactional
@Qualifier("userCreator")
public class UserCreatorImpl implements UserCreator {
    final protected SessionFactory sessionFactory;

    @Autowired
    public UserCreatorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveUser(User userToSave) {
        try {
            sessionFactory.getCurrentSession().save(userEntity);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new UserDuplicationException();
        }

        return userEntity;
    }
}
