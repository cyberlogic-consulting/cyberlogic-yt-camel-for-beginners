package camel.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class ExampleService {

    private final ProducerTemplate producerTemplate;

    public ExampleService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void doStuff() {
        producerTemplate.sendBody("direct:example-direct-route", "Hello World");
    }
}
