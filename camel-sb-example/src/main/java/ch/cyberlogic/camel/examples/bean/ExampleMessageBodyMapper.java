package ch.cyberlogic.camel.examples.bean;


import org.springframework.stereotype.Service;

@Service
public class ExampleMessageBodyMapper {

    public String map(String input) {
        return input.toUpperCase();
    }
}
