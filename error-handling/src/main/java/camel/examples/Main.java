package camel.examples;

import camel.examples.route.ErrorHandlerExampleRoute;
import camel.examples.route.ErrorHandlingConfigurationExample;
import camel.examples.route.OnExceptionExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class Main {
    public static void main(String[] args) {
        try (CamelContext camelContext = new DefaultCamelContext()) {

            camelContext.addRoutes(new ErrorHandlingConfigurationExample());

            camelContext.addRoutes(new OnExceptionExampleRoute());
            camelContext.addRoutes(new ErrorHandlerExampleRoute());

            camelContext.start();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}