package com.example.service.authentication.login.user.creation;

import com.example.domain.authentication.user.User;
import com.example.domain.authentication.user.creation.UserCreator;
import com.example.service.authentication.login.user.UserEntity;
import com.example.service.authentication.login.user.roles.UserRoleEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Repository
@Transactional
public class UserCreatorImpl implements UserCreator {
    final protected SessionFactory sessionFactory;

    public UserCreatorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User createUser(User user) {
        try {
            var userEntity = new UserEntity();

            userEntity.setRoles(
                    user.getRoles().stream().map(role -> {
                        var userRoleEntity = new UserRoleEntity();
                        userRoleEntity.setName(role);
                        userRoleEntity.setUser(userEntity);
                        return userRoleEntity;
                    }).collect(Collectors.toList())
            );

            sessionFactory.getCurrentSession().save(userEntity);
            return userEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
}
