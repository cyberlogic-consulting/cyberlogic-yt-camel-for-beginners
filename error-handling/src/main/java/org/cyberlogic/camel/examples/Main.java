package org.cyberlogic.camel.examples;

import org.cyberlogic.camel.examples.route.ErrorHandlerExampleRoute;
import org.cyberlogic.camel.examples.route.ErrorHandlingConfigurationExample;
import org.cyberlogic.camel.examples.route.OnExceptionExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {

            camelContext.addRoutes(new ErrorHandlingConfigurationExample());

            camelContext.addRoutes(new OnExceptionExampleRoute());
            camelContext.addRoutes(new ErrorHandlerExampleRoute());

            camelContext.start();
            Thread.sleep(2000);
        }
    }
}