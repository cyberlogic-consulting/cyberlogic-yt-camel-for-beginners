package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class BeanExampleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:src/main/resources/files?fileName=processor-example-bean.json&noop=true")
                .unmarshal().json()
                .log("Body: ${body}, Headers: ${headers}")
                .bean("my-transformer")
                .marshal().json()
                .to("file:src/main/resources/files/output?fileName=processor-example-bean.json");
    }
}