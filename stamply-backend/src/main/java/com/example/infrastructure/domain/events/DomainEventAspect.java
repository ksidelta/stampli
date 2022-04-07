package com.example.infrastructure.domain.events;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class DomainEventAspect {
    private final static Logger logger = Logger.getLogger(DomainEventAspect.class.getCanonicalName());

    final private ApplicationEventPublisher applicationEventPublisher;

    public DomainEventAspect(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Around("(execution(* save(..)) || execution(* remove(..))) && @target(org.springframework.stereotype.Repository)")
    public Object emitEvents(ProceedingJoinPoint joint) throws Throwable {
        final var returnedValue = joint.proceed();

        final var args = joint.getArgs();
        if (args.length == 1 && args[0] instanceof AbstractEventedAggregate) {
            final AbstractEventedAggregate aggregateRoot = (AbstractEventedAggregate) args[0];
            aggregateRoot.consumeEventsBy(applicationEventPublisher);

            logger.log(Level.INFO, "DomainEventAspect executed on: " + joint.getSignature().toLongString());
        } else {
            logger.log(Level.WARNING, "DomainEventAspect not executed on strange repository: " + joint.getSignature().getName());
        }

        return returnedValue;
    }
}
