package camel.examples.camelsbexample.bean;

import org.springframework.stereotype.Component;

@Component
public class ExampleMessageBodyMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
