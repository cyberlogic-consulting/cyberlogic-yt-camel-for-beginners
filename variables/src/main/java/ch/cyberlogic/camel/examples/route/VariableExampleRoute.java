package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class VariableExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "variableExampleRoute";

    @Override
    public void configure() throws Exception {
        from("timer:tick?period=3s")
                .routeId(ROUTE_ID)
                .setVariable("myVariable", constant("My variable value"))
                .transform(simple("Transformed body: ${variable.myVariable}"))
                .log("${body}");
    }
}
