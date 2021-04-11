package com.example.service.authentication.user.repository.creation;

import com.example.domain.authentication.user.entity.User;
import com.example.domain.authentication.user.repository.creation.UserCreationDto;
import com.example.domain.authentication.user.repository.creation.UserCreator;
import com.example.domain.authentication.user.repository.creation.UserDuplicationException;
import com.example.service.authentication.user.entity.UserEntity;
import com.example.service.authentication.roles.UserRoleEntity;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Repository
@Transactional
public class UserCreatorImpl implements UserCreator {
    final protected SessionFactory sessionFactory;

    @Autowired
    public UserCreatorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User createUser(UserCreationDto userCreationDto) {
        var userEntity = new UserEntity();

        userEntity.setEmail(userCreationDto.getEmail());
        userEntity.setRoles(
                userCreationDto.getRoles().stream().map(role ->
                        new UserRoleEntity(userEntity, role)
                ).collect(Collectors.toList())
        );

        try {
            sessionFactory.getCurrentSession().save(userEntity);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new UserDuplicationException();
        }

        return userEntity;
    }
}
