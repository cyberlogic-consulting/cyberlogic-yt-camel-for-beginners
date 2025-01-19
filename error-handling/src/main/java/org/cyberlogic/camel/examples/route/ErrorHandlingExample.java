package org.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public abstract class ErrorHandlingExample extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .log("Exception: ${exception}")
                .maximumRedeliveries(2)
                .redeliveryDelay(300);

        errorHandler(defaultErrorHandler());
    }
}
