package camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class GlobalVariableExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "globalVariableExampleRoute";

    @Override
    public void configure() throws Exception {
        from("timer:tick?period=3s")
                .routeId(ROUTE_ID)
                .log("Value of variable.global:myVariable is: ${variable.global:myVariable}");
    }
}
