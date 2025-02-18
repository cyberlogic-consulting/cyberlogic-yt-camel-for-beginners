package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SoapServerFromJavaRoute extends RouteBuilder {

    public static final String ID = "SoapServerFromJavaRoute";

    @Override
    public void configure() {
        from("cxf:bean:petstoreFromJava")
                .routeId(ID)
                .toD("bean:petServiceFromJava?method=${header.operationName}");
    }
}
