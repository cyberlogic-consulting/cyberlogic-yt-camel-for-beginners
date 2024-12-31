package camel.example.bean;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleMessageHeaderMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
