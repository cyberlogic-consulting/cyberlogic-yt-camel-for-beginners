package ch.cyberlogic.camel.examples.jms;

import io.smallrye.common.annotation.Identifier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.ConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

@ApplicationScoped
public class JmsComponentProducer {

    //@Named("artemis")
    public JmsComponent artemis (@Identifier("artemisMQFactory")
                                     ConnectionFactory connectionFactory) {
        JmsComponent result = new JmsComponent();
        result.setConnectionFactory(connectionFactory);
        return result;
    }

    //@Named("rabbitmq")
    public JmsComponent rabbitmq (@Identifier("rabbitmqMQFactory")
                                      ConnectionFactory connectionFactory) {
        JmsComponent result = new JmsComponent();
        result.setConnectionFactory(connectionFactory);
        return result;
    }
}
