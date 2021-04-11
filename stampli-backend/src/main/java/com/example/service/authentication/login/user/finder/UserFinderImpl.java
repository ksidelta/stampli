package com.example.service.authentication.login.user.finder;

import com.example.domain.authentication.user.User;
import com.example.domain.authentication.user.finder.UserFinder;
import com.example.service.authentication.login.user.UserAuthenticationPasswordEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class UserFinderImpl implements UserFinder {
    Log logger = LogFactory.getLog(getClass());

    protected SessionFactory sessionFactory;

    public UserFinderImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws BadCredentialsException {
        try {
            final var userAuthenticationPasswordEntity = sessionFactory.getCurrentSession()
                    .createQuery(
                            "SELECT AUTH FROM UserAuthenticationPasswordEntity AUTH  "
                                    + "LEFT JOIN UserEntity USER ON USER.id = AUTH.userId "
                                    + "WHERE USER.email=:email AND AUTH.password=:password"
                            , UserAuthenticationPasswordEntity.class)
                    .setParameter("email", username)
                    .setParameter("password", password)
                    .getSingleResult();

            return userAuthenticationPasswordEntity.getUser();
        } catch (NoResultException ex) {
            throw new BadCredentialsException("user not found");
        }
    }
}
