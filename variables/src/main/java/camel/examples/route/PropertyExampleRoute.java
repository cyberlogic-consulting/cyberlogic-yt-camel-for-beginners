package camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class PropertyExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "propertyExampleRoute";

    @Override
    public void configure() throws Exception {
        from("timer:tick?period=3s")
                .routeId(ROUTE_ID)
                .setProperty("myProperty", constant("My property value"))
                .transform(simple("Transformed body: ${exchangeProperty.myProperty}"))
                .log("${body}");
    }
}
