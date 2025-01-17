package camel.examples;

import camel.examples.bean.MyExampleMapper;
import camel.examples.route.BeanExampleRoute;
import camel.examples.route.ProcessorExampleRoute;
import camel.examples.route.TransformExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


public class Main {
    public static void main(String[] args) {
        try (CamelContext camelContext = new DefaultCamelContext()) {
            camelContext.addRoutes(new ProcessorExampleRoute());
            camelContext.addRoutes(new BeanExampleRoute());
            camelContext.addRoutes(new TransformExampleRoute());

            camelContext.getRegistry().bind("my-transformer", new MyExampleMapper());

            camelContext.start();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}