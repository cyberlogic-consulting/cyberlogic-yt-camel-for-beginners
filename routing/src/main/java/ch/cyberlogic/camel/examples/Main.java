package ch.cyberlogic.camel.examples;

import ch.cyberlogic.camel.examples.route.ChoiceExampleRoute;
import ch.cyberlogic.camel.examples.route.RecipientListExampleRoute;
import jakarta.jms.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.qpid.jms.JmsConnectionFactory;


public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {
            camelContext.addRoutes(new ChoiceExampleRoute());
            camelContext.addRoutes(new RecipientListExampleRoute());
            JmsComponent jms = camelContext.getComponent("jms", JmsComponent.class);
            jms.setConnectionFactory(createConnectionFactory());

            camelContext.start();
            Thread.sleep(2000000);
        }
    }

    private static ConnectionFactory createConnectionFactory() {
        JmsConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:61616");
        connectionFactory.setUsername("artemis");
        connectionFactory.setPassword("artemis");
        return connectionFactory;

    }
}