package camel.examples;

import camel.examples.bean.ExampleFileFilter;
import camel.examples.route.ExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;


public class Main {
    public static void main(String[] args) {
        try (CamelContext camelContext = new DefaultCamelContext()) {
            camelContext.addRoutes(new ExampleRoute());
            PropertiesComponent properties = camelContext.getPropertiesComponent();
            properties.setLocation("classpath:my-camel.properties");

            camelContext.getRegistry().bind("my-file-filter", new ExampleFileFilter());
            
            camelContext.start();
            Thread.sleep(2000);
            camelContext.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}