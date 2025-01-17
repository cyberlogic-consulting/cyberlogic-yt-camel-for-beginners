package camel.examples;

import camel.examples.component.LocalSftp;
import camel.examples.route.ExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {

            camelContext.addRoutes(new ExampleRoute());
            camelContext.addComponent("localSftp", new LocalSftp().localSftp());
            camelContext.getPropertiesComponent().setLocation("classpath:application.properties");

            camelContext.start();
            Thread.sleep(3000000);
        }
    }
}