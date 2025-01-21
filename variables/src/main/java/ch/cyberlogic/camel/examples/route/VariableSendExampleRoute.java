package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class VariableSendExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "variableSendExampleRoute";

    public static final String SERVICE_ROUTE_ID = "variableSendExampleServiceRoute";

    @Override
    public void configure() {
        from("timer:tick?period=3s")
                .routeId(ROUTE_ID)
                .setBody(constant("Initial request body"))
                .setVariable("requestToService", constant("My request"))
                .toV(
                        "direct:variable-send-example-service",
                        "requestToService",
                        "responseFromService"
                )
                .log("Body: ${body}; variable: ${variable.responseFromService}");

        from("direct:variable-send-example-service")
                .routeId(SERVICE_ROUTE_ID)
                .log("Service received request: ${body}")
                .setBody(constant("Response from service"));

    }
}
