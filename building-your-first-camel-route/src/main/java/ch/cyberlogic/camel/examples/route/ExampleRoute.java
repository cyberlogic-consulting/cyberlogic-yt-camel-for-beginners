package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class ExampleRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("file:src/main/resources/files?noop=true")
                .log("Message: ${body}, Headers: ${headers}")
                .to("file:src/main/resources/files/output");
    }
}