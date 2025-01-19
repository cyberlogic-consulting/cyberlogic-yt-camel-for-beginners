package org.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class ExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "exampleRoute";

    @Override
    public void configure() throws Exception {
        from("file:src/main/resources/files/input?noop=true")
                .routeId(ROUTE_ID)
                .bean("exampleBean")
                .to("file:src/main/resources/files/output");
    }
}