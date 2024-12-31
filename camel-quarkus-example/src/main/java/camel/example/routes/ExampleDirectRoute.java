package camel.example.routes;

import org.apache.camel.builder.RouteBuilder;

public class ExampleDirectRoute extends RouteBuilder {

    public static final String ID = "ExampleDirectRoute";

    @Override
    public void configure() throws Exception {
        from("direct:example-direct-route")
                .routeId(ID)
                .log("Received request: ${body}");
    }
}
