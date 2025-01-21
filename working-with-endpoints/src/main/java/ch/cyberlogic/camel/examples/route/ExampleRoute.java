package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class ExampleRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("file:src/main/resources/files?noop={{my.camel.property.noop}}&filter=#my-file-filter")
                .log("Message: ${body}, Headers: ${headers}")
                .toD("file:src/main/resources/files/output/${body}?fileName=RAW(&file.txt)");
    }
}