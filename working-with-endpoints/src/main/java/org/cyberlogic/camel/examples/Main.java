package org.cyberlogic.camel.examples;

import org.cyberlogic.camel.examples.bean.ExampleFileFilter;
import org.cyberlogic.camel.examples.route.ExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;


public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {
            camelContext.addRoutes(new ExampleRoute());
            PropertiesComponent properties = camelContext.getPropertiesComponent();
            properties.setLocation("classpath:my-camel.properties");

            camelContext.getRegistry().bind("my-file-filter", new ExampleFileFilter());
            
            camelContext.start();
            Thread.sleep(2000);
        }
    }
}