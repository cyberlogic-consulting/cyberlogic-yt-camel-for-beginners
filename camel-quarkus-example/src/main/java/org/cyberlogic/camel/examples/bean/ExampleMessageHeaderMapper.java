package org.cyberlogic.camel.examples.bean;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleMessageHeaderMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
