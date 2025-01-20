package ch.cyberlogic.camel.examples.bean;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

@ApplicationScoped
@Named("exampleMessageTransformer")
public class ExampleMessageTransformer {

    private final ExampleMessageBodyMapper bodyMapper;

    private final ExampleMessageHeaderMapper headerMapper;

    public ExampleMessageTransformer(ExampleMessageBodyMapper bodyMapper, ExampleMessageHeaderMapper headerMapper) {
        this.bodyMapper = bodyMapper;
        this.headerMapper = headerMapper;
    }

    public void transform(Exchange exchange) {
        Message msg = exchange.getMessage();
        String header = "examples-header";
        msg.setBody(bodyMapper.map(msg.getBody(String.class)));
        msg.setHeader(header, headerMapper.map(msg.getHeader(header, String.class)));
    }
}
