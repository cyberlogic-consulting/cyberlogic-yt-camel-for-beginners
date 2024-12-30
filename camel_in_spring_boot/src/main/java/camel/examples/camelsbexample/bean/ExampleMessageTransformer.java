package camel.examples.camelsbexample.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;

@Component
public class ExampleMessageTransformer {

    private final ExampleMessageBodyMapper bodyMapper;

    private final ExampleMessageHeaderMapper headerMapper;

    public ExampleMessageTransformer(ExampleMessageBodyMapper bodyMapper, ExampleMessageHeaderMapper headerMapper) {
        this.bodyMapper = bodyMapper;
        this.headerMapper = headerMapper;
    }

    public void transform(Exchange exchange) {
        Message msg = exchange.getMessage();
        String header = "example-header";
        msg.setBody(bodyMapper.map(msg.getBody(String.class)));
        msg.setHeader(header, headerMapper.map(msg.getHeader(header, String.class)));
    }
}