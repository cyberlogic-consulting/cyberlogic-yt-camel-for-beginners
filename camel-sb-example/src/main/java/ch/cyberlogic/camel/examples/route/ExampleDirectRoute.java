package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExampleDirectRoute extends RouteBuilder {

    public static final String ID = "ExampleDirectRoute";

    @Override
    public void configure() throws Exception {
        from("direct:example-direct-route")
                .routeId(ID)
                .log("Received request: ${body}");
    }
}
