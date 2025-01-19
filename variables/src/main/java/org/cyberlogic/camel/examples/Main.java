package org.cyberlogic.camel.examples;

import org.cyberlogic.camel.examples.repository.ExampleVariableRepository;
import org.cyberlogic.camel.examples.route.VariableSendExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {

            camelContext.addRoutes(new VariableSendExampleRoute());

            camelContext.setVariable("myVariable", "My Variable Value");
            camelContext.getRegistry().bind(ExampleVariableRepository.ID, new ExampleVariableRepository());

            camelContext.start();
            Thread.sleep(20000);
        }
    }
}