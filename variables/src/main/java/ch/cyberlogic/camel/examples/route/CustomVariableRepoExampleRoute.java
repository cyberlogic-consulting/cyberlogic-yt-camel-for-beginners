package ch.cyberlogic.camel.examples.route;

import ch.cyberlogic.camel.examples.repository.ExampleVariableRepository;
import org.apache.camel.builder.RouteBuilder;

public class CustomVariableRepoExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "customVariableRepoExampleRoute";

    @Override
    public void configure() throws Exception {
        from("timer:tick?period=3s")
                .routeId(ROUTE_ID)
                .setVariable(
                        ExampleVariableRepository.ID + ":myVariable",
                        constant("My variable value"))
                .log("Value of variable." + ExampleVariableRepository.ID + ":myVariable : " +
                        "${variable." + ExampleVariableRepository.ID + ":myVariable}");
    }
}