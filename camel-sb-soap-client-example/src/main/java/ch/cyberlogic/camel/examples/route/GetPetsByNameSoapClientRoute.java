package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.springframework.stereotype.Component;

import static ch.cyberlogic.camel.examples.util.SoapConstants.EXAMPLE_NAME;
import static ch.cyberlogic.camel.examples.util.SoapConstants.GET_PETS_BY_NAME_OPERATION;
import static ch.cyberlogic.camel.examples.util.SoapConstants.NAMESPACE;
import static ch.cyberlogic.camel.examples.util.SoapConstants.SERVICE_CLASS;

@Component
public class GetPetsByNameSoapClientRoute extends RouteBuilder {
    public static final String GET_PETS_BY_NAME_ROUTE_ID = "getPetsByNameSoapClientRoute";

    @Override
    public void configure() {
        from("timer:tick?period=10s")
                .routeId(GET_PETS_BY_NAME_ROUTE_ID)
                .process(exchange -> exchange.getMessage().setBody(EXAMPLE_NAME))
                .setHeader(CxfConstants.OPERATION_NAME,
                        constant(GET_PETS_BY_NAME_OPERATION))
                .setHeader(CxfConstants.OPERATION_NAMESPACE,
                        constant(NAMESPACE))
                .to("cxf://http://localhost:8080/services/pets"
                        + "?serviceClass=" + SERVICE_CLASS
                        + "&wsdlURL=/wsdl/PetService.wsdl")
                .log("Got ${body[0].size()} pets with name \"" + EXAMPLE_NAME + "\"");
    }
}
