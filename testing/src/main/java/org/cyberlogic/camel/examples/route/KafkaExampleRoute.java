package org.cyberlogic.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;

public class KafkaExampleRoute extends RouteBuilder {

    public static final String ROUTE_ID = "kafkaExampleRoute";

    @Override
    public void configure() throws Exception {
        from("kafka:{{camel.kafka-route.topic}}?brokers={{camel.kafka-route.brokers-string}}" +
                "&valueDeserializer=org.apache.kafka.common.serialization.StringDeserializer" +
                "&autoOffsetReset=earliest")
                .routeId(ROUTE_ID)
                .log("Received from kafka: ${body}")
                .process(exchange -> exchange.getMessage().setBody(
                        exchange.getIn().getBody(String.class).toLowerCase()))
                .to("file:{{camel.kafka-route.file-path}}?fileName={{camel.kafka-route.file-name}}");
    }
}