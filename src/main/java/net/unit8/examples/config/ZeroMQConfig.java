package net.unit8.examples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.zeromq.jms.ZmqConnectionFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class ZeroMQConfig {
    private static final String QUEUE_NAME = "project";
    private static final String QUEUE_CLIENT_URI = "jms:queue:" + QUEUE_NAME
        + "?socket.addr=inproc://queue1&socket.bind=false&redelivery.retry=0";
    private static final String QUEUE_SERVER_URI = "jms:queue:" + QUEUE_NAME
        + "?socket.addr=inproc://queue1&context.ioThreads=1&redelivery.retry=0";

    private ConnectionFactory connectionFactory() {
        final ConnectionFactory connectionFactory = new ZmqConnectionFactory(new String[] { QUEUE_CLIENT_URI });
        return new CachingConnectionFactory(connectionFactory);
    }

    /**
     * Enable JMS listener annotated endpoints that are created under the cover by a JmsListenerContainerFactory.
     * @return  return the JMS listener
     */
    @Bean
    public DefaultJmsListenerContainerFactory myJmsListenerContainerFactory() {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(new ZmqConnectionFactory(new String[] { QUEUE_SERVER_URI }));
        //factory.setDestinationResolver(destinationResolver());
        factory.setConcurrency("5");

        return factory;
    }

    /**
     * @return return the JMS template to send a message
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        final JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(QUEUE_NAME);

        return template;
    }

}
