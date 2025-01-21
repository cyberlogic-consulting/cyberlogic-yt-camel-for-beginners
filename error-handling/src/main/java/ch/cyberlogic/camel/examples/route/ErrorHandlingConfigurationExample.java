package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteConfigurationBuilder;

public class ErrorHandlingConfigurationExample extends RouteConfigurationBuilder {

    @Override
    public void configuration() {
        routeConfiguration("example-error-handling").
        onException(Exception.class)
                .log("Exception: ${exception}")
                .maximumRedeliveries(2)
                .redeliveryDelay(300);
    }
}
