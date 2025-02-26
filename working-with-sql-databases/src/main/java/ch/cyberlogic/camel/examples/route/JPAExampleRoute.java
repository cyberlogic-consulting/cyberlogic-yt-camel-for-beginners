package ch.cyberlogic.camel.examples.route;

import ch.cyberlogic.camel.examples.entity.Pet;
import ch.cyberlogic.camel.examples.entity.Pet.PetType;
import java.time.LocalDate;
import org.apache.camel.builder.RouteBuilder;

public class JPAExampleRoute extends RouteBuilder {

    public static String ROUTE_ID_SELECT = "JPAExampleRouteSelect";
    public static String ROUTE_ID_INSERT = "JPAExampleRouteInsert";

    @Override
    public void configure() {
        from("timer:tick?period=5s")
                .routeId(ROUTE_ID_INSERT)
                .process(exchange -> {
                    Pet pet = new Pet();
                    pet.setName("doggie");
                    pet.setStatus("Available");
                    pet.setType(PetType.Dog);
                    pet.setBirthDate(LocalDate.now());
                    exchange.getMessage().setBody(pet);
                })
                .to("jpa://ch.cyberlogic.camel.examples.entity.Pet");

        from("timer:tick?period=10s")
                .routeId(ROUTE_ID_SELECT)
                .setHeader("petName", constant("doggie"))
                .toD("jpa://ch.cyberlogic.camel.examples.entity.Pet" +
                        "?query=select p from ch.cyberlogic.camel.examples.entity.Pet p " +
                        "where p.name='${header.petName}'")
                .log("${body}");
    }
}
