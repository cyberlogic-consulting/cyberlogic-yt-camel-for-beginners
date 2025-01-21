package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class VariableReceiveExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "variableReceiveExampleRoute";

    public static final String SERVICE_ROUTE_ID = "variableReceiveExampleServiceRoute";

    @Override
    public void configure() {
        from("timer:tick?period=3s")
                .routeId(ROUTE_ID)
                .setBody(constant("Initial request body"))
                .toV("direct:variable-receive-example-service", null, "responseFromService")
                .log("Body: ${body}; variable: ${variable.responseFromService}");

        from("direct:variable-receive-example-service")
                .routeId(SERVICE_ROUTE_ID)
                .setBody(constant("Response from service"));

    }
}
