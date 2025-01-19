package org.cyberlogic.camel.examples;

import org.cyberlogic.camel.examples.bean.MyExampleMapper;
import org.cyberlogic.camel.examples.route.BeanExampleRoute;
import org.cyberlogic.camel.examples.route.ProcessorExampleRoute;
import org.cyberlogic.camel.examples.route.TransformExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class Main {
    public static void main(String[] args) throws Exception {
        try (CamelContext camelContext = new DefaultCamelContext()) {
            camelContext.addRoutes(new ProcessorExampleRoute());
            camelContext.addRoutes(new BeanExampleRoute());
            camelContext.addRoutes(new TransformExampleRoute());

            camelContext.getRegistry().bind("my-transformer", new MyExampleMapper());

            camelContext.start();
            Thread.sleep(2000);
        }
    }
}