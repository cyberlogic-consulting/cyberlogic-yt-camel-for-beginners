package ch.cyberlogic.camel.examples;

import ch.cyberlogic.camel.examples.bean.ExampleBean;
import ch.cyberlogic.camel.examples.bean.MethodBindingExampleBean;
import ch.cyberlogic.camel.examples.route.ExampleRoute;
import ch.cyberlogic.camel.examples.route.MethodBindingExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {

            camelContext.addRoutes(new ExampleRoute());
            camelContext.addRoutes(new MethodBindingExampleRoute());

            camelContext.getRegistry().bind("exampleBean", new ExampleBean());
            camelContext.getRegistry().bind("methodBindingExampleBean", new MethodBindingExampleBean());

            camelContext.start();
            Thread.sleep(2000);
        }
    }
}