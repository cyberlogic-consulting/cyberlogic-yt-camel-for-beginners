package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class ChoiceExampleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:input.queue")
                .log("Body: ${body}, Headers: ${headers}")
                .choice()
                .when(exchange -> {
                    Object requestType = exchange.getMessage().getHeader("requestType");
                    if (requestType != null) {
                        return "statement".equals(requestType);
                    }
                    return false;
                })
                    .to("jms:statement.queue")
                .when(simple("${header.requestType} == 'paymentDetails'"))
                    .to("jms:request.details.queue")
                .otherwise()
                    .to("jms:unrecognised.queue")
                .end()
                .log("Processing ended.")
        ;
    }
}