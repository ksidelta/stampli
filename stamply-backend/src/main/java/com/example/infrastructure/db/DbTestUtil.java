package com.example.infrastructure.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Component
@Transactional
public class DbTestUtil {
    Logger logger = Logger.getLogger(DbTestUtil.class.getName());

    SessionFactory sessionFactory;

    public DbTestUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void removeAll() {
        forEachAggregateClass(this::purge);
    }

    protected void forEachAggregateClass(Consumer<Class<?>> consumer) {
        final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Aggregate.*")));
        scanner.addExcludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Abstract.*")));
        scanner.addExcludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Repository.*")));
        scanner.addExcludeFilter(new AbstractClassTestingTypeFilter() {
            @Override
            protected boolean match(ClassMetadata metadata) {
                return metadata.getEnclosingClassName() != null;
            }
        });

        final Set<BeanDefinition> beans = scanner.findCandidateComponents("com.example");


        beans.forEach(bean -> {
            try {
                consumer.accept(Class.forName(bean.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }


    protected void purge(Class<?> klass) {
        final var className = klass.getSimpleName();

        sessionFactory.getCurrentSession()
                .createQuery("SELECT e FROM " + className + " e", klass)
                .getResultStream()
                .forEach(entity -> sessionFactory.getCurrentSession().delete(entity));
    }
}
