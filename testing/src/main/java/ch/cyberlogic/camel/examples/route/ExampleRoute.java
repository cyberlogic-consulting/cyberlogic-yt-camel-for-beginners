package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class ExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "exampleRoute";

    @Override
    public void configure() {
        from("file:src/main/resources/files?noop=true")
                .routeId(ROUTE_ID)
                .process(exchange -> exchange.getMessage().setBody(
                        exchange.getIn().getBody(String.class).toUpperCase()))
                .to("file:src/main/resources/files/output");
    }
}