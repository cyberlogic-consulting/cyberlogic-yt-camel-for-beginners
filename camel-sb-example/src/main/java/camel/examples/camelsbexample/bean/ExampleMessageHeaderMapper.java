package camel.examples.camelsbexample.bean;

import org.springframework.stereotype.Component;

@Component
public class ExampleMessageHeaderMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
