package camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class RouteVariableExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "routeVariableExampleRoute";

    public static final String SECOND_ROUTE_ID = "routeVariableExampleSecondRoute";

    @Override
    public void configure() throws Exception {
        from("timer:tick?period=3s")
                .routeId(ROUTE_ID)
                .setVariable("route:myVariable", constant("My variable value"))
                .to("direct:route-variable-example-second-route");

        from("direct:route-variable-example-second-route")
                .id(SECOND_ROUTE_ID)
                .log(
                        "Value of variable.route:" + ROUTE_ID + ":myVariable is: " +
                        "${variable.route:" + ROUTE_ID + ":myVariable}"
                )
                .log("Value of variable.myVariable is: ${variable.myVariable}");
    }
}
