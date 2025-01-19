package org.cyberlogic.camel.examples.bean;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleMessageBodyMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
