package com.example.infrastructure.jms;

import com.example.BaseTestConfiguration;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Disabled
public class JMSIntegration {
    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    public void whenMessageIsSentThenItSucceeds() {
        jmsTemplate.send("SampleQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Text");
            }
        });
    }

    @Test
    public void whenMessageIsSentThenItCanBeRetrieved() throws JMSException {
        jmsTemplate.send("SampleQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Text");
            }
        });

        final var message = jmsTemplate.receive("SampleQueue");

        MatcherAssert.assertThat(message, Matchers.instanceOf(TextMessage.class));
        MatcherAssert.assertThat(((TextMessage) message).getText(), CoreMatchers.equalTo("Text"));
    }
}
