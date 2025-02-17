package ch.cyberlogic.camel.examples;

import ch.cyberlogic.camel.examples.route.ExampleRestRoute;
import ch.cyberlogic.camel.examples.storage.ExamplePetStorage;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {

            camelContext.addRoutes(new ExampleRestRoute());
            camelContext.getRegistry().bind("examplePetStorage", new ExamplePetStorage());

            camelContext.start();
            Thread.sleep(3000000);
        }
    }
}