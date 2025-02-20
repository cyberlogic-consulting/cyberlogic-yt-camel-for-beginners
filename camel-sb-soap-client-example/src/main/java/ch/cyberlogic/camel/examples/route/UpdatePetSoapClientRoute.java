package ch.cyberlogic.camel.examples.route;

import ch.cyberlogic.camel.examples.petservice.Pet;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.datatype.DatatypeFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.springframework.stereotype.Component;

import static ch.cyberlogic.camel.examples.util.SoapConstants.EXAMPLE_NAME;
import static ch.cyberlogic.camel.examples.util.SoapConstants.NAMESPACE;
import static ch.cyberlogic.camel.examples.util.SoapConstants.PET_STATUSES;
import static ch.cyberlogic.camel.examples.util.SoapConstants.PET_TYPES;
import static ch.cyberlogic.camel.examples.util.SoapConstants.SERVICE_CLASS;
import static ch.cyberlogic.camel.examples.util.SoapConstants.UPDATE_PET_OPERATION;

@Component
public class UpdatePetSoapClientRoute extends RouteBuilder {

    public static final String UPDATE_PET_ROUTE_ID = "updatePetSoapClientRoute";

    private static final AtomicLong petIdCounter = new AtomicLong();

    @Override
    public void configure() {
        from("timer:tick?period=3s")
                .routeId(UPDATE_PET_ROUTE_ID)
                .process(exchange -> {
                    Random random = new Random();

                    Pet pet = new Pet();
                    pet.setName(EXAMPLE_NAME);
                    pet.setPetId(petIdCounter.incrementAndGet());
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.setTime(new Date());
                    pet.setBirthDate(
                            DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(gregorianCalendar)
                    );
                    pet.setStatus(PET_STATUSES[random.nextInt(PET_STATUSES.length)]);
                    pet.setType(PET_TYPES[random.nextInt(PET_TYPES.length)]);

                    exchange.getMessage().setBody(pet);
                })
                .setHeader(CxfConstants.OPERATION_NAME,
                        constant(UPDATE_PET_OPERATION))
                .setHeader(CxfConstants.OPERATION_NAMESPACE,
                        constant(NAMESPACE))
                .to("cxf://http://localhost:8080/services/pets"
                        + "?serviceClass=" + SERVICE_CLASS
                        + "&wsdlURL=/wsdl/PetService.wsdl")
                .log("Pet created with id: ${body.getPetId}");
    }
}
