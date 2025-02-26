package ch.cyberlogic.camel.examples;

import ch.cyberlogic.camel.examples.configuration.DataSourceConfiguration;
import ch.cyberlogic.camel.examples.route.SqlExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {

            camelContext.getRegistry().bind("pgDataSource", DataSourceConfiguration.getDataSource());
            camelContext.addRoutes(new SqlExampleRoute());

            camelContext.start();
            Thread.sleep(3000000);
        }
    }
}