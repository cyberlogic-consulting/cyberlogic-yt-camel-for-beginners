package camel.examples.camelsbexample.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private final ProducerTemplate producerTemplate;

    public ExampleService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void doStuff() {
        producerTemplate.sendBody("direct:example-direct-route", "Hello World");
    }
}
