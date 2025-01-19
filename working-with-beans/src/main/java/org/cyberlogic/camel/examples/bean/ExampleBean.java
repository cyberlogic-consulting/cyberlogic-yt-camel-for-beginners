package org.cyberlogic.camel.examples.bean;

import org.apache.camel.Exchange;

public class ExampleBean {
    public String doStuff(Exchange exchange) {
        return exchange.getMessage().getBody(String.class).toUpperCase();
    }

}
