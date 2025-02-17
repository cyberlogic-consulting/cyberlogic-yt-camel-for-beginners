package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExampleRoute extends RouteBuilder {

    public static final String ID = "ExampleRoute";

    @Override
    public void configure() {
        from("jms:{{example.queue.input.name}}")
                .routeId(ID)
                .log("Received request: ${body}")
                .bean("exampleMessageTransformer")
                .to("jms:{{example.queue.output.name}}");
    }
}
