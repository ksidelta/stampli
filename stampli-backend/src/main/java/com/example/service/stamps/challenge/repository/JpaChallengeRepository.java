package com.example.service.stamps.challenge.repository;

import com.example.domain.context.challenge.ChallengeOwnerAggregate;
import com.example.domain.context.challenge.ChallengeRepository;
import org.hibernate.SessionFactory;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class JpaChallengeRepository implements ChallengeRepository {

    SessionFactory sessionFactory;

    public JpaChallengeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(ChallengeOwnerAggregate challengeOwnerAggregate) {
        sessionFactory.getCurrentSession().save(challengeOwnerAggregate);
    }

    @Override
    @Transactional
    public Optional<ChallengeOwnerAggregate> findByIssuerAndBusiness(Integer issuerId, Integer businessId) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT ch FROM ChallengeOwnerAggregate ch WHERE ch.challengeId.businessId = :businessId AND ch.challengeId.issuerId = :issuerId", ChallengeOwnerAggregate.class)
                .setParameter("businessId", businessId)
                .setParameter("issuerId", issuerId)
                .uniqueResultOptional();
    }
}
