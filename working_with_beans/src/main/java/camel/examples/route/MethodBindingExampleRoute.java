package camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class MethodBindingExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "methodBindingExampleRoute";

    @Override
    public void configure() throws Exception {
        from("file:src/main/resources/files/input/binding?noop=true")
                .routeId(ROUTE_ID)
                .bean("methodBindingExampleBean", "transform(${body})")
                .to("file:src/main/resources/files/output/binding");
    }
}