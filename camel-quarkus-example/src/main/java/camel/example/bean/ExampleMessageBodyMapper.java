package camel.example.bean;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleMessageBodyMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
