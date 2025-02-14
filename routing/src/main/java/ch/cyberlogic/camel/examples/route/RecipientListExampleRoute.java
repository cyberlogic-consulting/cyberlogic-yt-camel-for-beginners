package ch.cyberlogic.camel.examples.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipientListExampleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:recipient.list.queue")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) {
                        String departments = exchange.getMessage().getHeader("departments", String.class);
                        List<String> whereToSend = new ArrayList<>();
                        if (departments != null) {
                            Arrays.asList(departments.split(",")).forEach(department -> whereToSend.add("jms:" + department + ".queue"));
                        }
                        exchange.getMessage().setHeader("whereToSend", whereToSend);
                    }
                })
                .recipientList(header("whereToSend"));
    }
}