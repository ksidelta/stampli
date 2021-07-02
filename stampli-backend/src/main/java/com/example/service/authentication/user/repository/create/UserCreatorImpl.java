package com.example.service.authentication.user.repository.create;

import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;
import com.example.domain.context.authentication.user.repository.create.UserCreator;
import com.example.domain.context.authentication.user.repository.create.UserDuplicationException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public void saveUser(AbstractUserAggregate userToSave) {
        try {
            sessionFactory.getCurrentSession().save(userToSave);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new UserDuplicationException();
        }
    }
}
