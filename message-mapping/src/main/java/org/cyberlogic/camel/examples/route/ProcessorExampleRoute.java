package org.cyberlogic.camel.examples.route;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.builder.RouteBuilder;

public class ProcessorExampleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:src/main/resources/files?fileName=processor-example.json&noop=true")
                .unmarshal().json()
                .log("Body: ${body}, Headers: ${headers}")
                .process(exchange -> {
                    ObjectNode body = exchange.getMessage().getBody(ObjectNode.class);

                    body.put("newFiled", "newValue");

                    exchange.getMessage().setBody(body);
                })
                .marshal().json()
                .to("file:src/main/resources/files/output?fileName=processor-example.json");
    }
}