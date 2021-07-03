package com.example.service.authentication.user.repository.find;

import com.example.domain.context.authentication.user.entity.EmailPasswordUserAggregate;
import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;
import com.example.domain.context.authentication.user.entity.TokenUserAggregate;
import com.example.domain.context.authentication.user.repository.find.UserFinder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
@Qualifier("userFinder")
public class UserFinderImpl implements UserFinder {
    protected SessionFactory sessionFactory;

    @Autowired
    public UserFinderImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public AbstractUserAggregate findByUsernameAndPassword(String username, String password) throws BadCredentialsException {
        try {
            final var userAuthenticationPasswordEntity = sessionFactory.getCurrentSession()
                    .createQuery(
                            "SELECT AUTH FROM EmailPasswordUserAggregate AUTH  "
                                    + "WHERE AUTH.login=:login AND AUTH.password=:password"
                            , EmailPasswordUserAggregate.class)
                    .setParameter("login", username)
                    .setParameter("password", password)
                    .getSingleResult();

            return userAuthenticationPasswordEntity;
        } catch (NoResultException ex) {
            throw new BadCredentialsException("user not found");
        }
    }

    @Override
    public AbstractUserAggregate findByIssuerAndUid(String issuer, String uid) throws BadCredentialsException {
        try {
            final var userAuthenticationPasswordEntity = sessionFactory.getCurrentSession()
                    .createQuery(
                            "SELECT AUTH FROM TokenUserAggregate AUTH  "
                                    + "WHERE AUTH.issuer=:issuer AND AUTH.uid=:uid"
                            , TokenUserAggregate.class)
                    .setParameter("issuer", issuer)
                    .setParameter("uid", uid)
                    .getSingleResult();

            return userAuthenticationPasswordEntity;
        } catch (NoResultException ex) {
            throw new BadCredentialsException("user not found");
        }
    }
}
