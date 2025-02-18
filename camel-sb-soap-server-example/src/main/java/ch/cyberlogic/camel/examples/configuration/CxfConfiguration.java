package ch.cyberlogic.camel.examples.configuration;

import ch.cyberlogic.camel.examples.petservice.PetService;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfiguration {
    @Bean
    CxfEndpoint petstoreFromWSDL() {
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setWsdlURL("wsdl/PetService.wsdl");
        endpoint.setAddress("/pets");
        endpoint.setServiceClass(PetService.class);

        Map<String, Object> properties = new HashMap<>();
        properties.put("schema-validation-enabled", "true");
        endpoint.setProperties(properties);
        return endpoint;
    }

    @Bean
    CxfEndpoint petstoreFromJava() {
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setAddress("/pets");
        endpoint.setServiceClass(ch.cyberlogic.camel.examples.service.PetService.class);

        Map<String, Object> properties = new HashMap<>();
        properties.put("schema-validation-enabled", "true");
        endpoint.setProperties(properties);
        return endpoint;
    }
}
