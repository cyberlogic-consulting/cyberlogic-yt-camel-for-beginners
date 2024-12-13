package camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class OnExceptionExampleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:ticker?period=1s")
                .routeConfigurationId("example-error-handling")
                .setBody(simple("Hello World"))
                .process(exchange -> {
                    throw new RuntimeException("Test exception");
                })
                .log("Ended route with body: ${body}");

        from("timer:ticker_two?period=1s")
                .routeConfigurationId("example-error-handling")
                .setBody(simple("Hello World"))
                .process(exchange -> {
                    throw new RuntimeException("Test exception");
                })
                .log("Ended route with body: ${body}");
    }
}