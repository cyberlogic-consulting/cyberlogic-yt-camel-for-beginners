package ch.cyberlogic.camel.examples.bean;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Exchange;

public class MyExampleMapper {

    public void transform(Exchange exchange) {
        ObjectNode body = exchange.getMessage().getBody(ObjectNode.class);

        body.put("newFiled", "newValue");

        exchange.getMessage().setBody(body);
    }
}
