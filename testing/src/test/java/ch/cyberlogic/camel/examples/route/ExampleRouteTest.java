package ch.cyberlogic.camel.examples.route;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ExampleRouteTest {

    private static CamelContext camelContext;

    private static final String mockExampleRouteToFileOutput = "mock:exampleRouteToFileOutput";

    private static final String directExampleRouteTest = "direct:exampleRouteTest";

    @BeforeAll
    public static void setUp() throws Exception {
        camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new ExampleRoute());
        AdviceWith.adviceWith(
                camelContext,
                ExampleRoute.ROUTE_ID,
                route -> route
                        .interceptSendToEndpoint("file:src/main/resources/files/output")
                        .skipSendToOriginalEndpoint()
                        .to(mockExampleRouteToFileOutput)
        );
        AdviceWith.adviceWith(
                camelContext,
                ExampleRoute.ROUTE_ID,
                route -> route
                        .replaceFromWith(directExampleRouteTest)
        );


        camelContext.start();
    }

    @AfterAll
    public static void tearDown() throws Exception {
        camelContext.stop();
    }

    @Test
    void testExampleRoute() throws Exception {
        String testBody = "Bye World";
        MockEndpoint toFileOutput = camelContext.getEndpoint(mockExampleRouteToFileOutput, MockEndpoint.class);
        toFileOutput.expectedMessageCount(1);
        toFileOutput.message(0).body().isEqualTo(testBody.toUpperCase());
        ProducerTemplate producer = camelContext.createProducerTemplate();

        producer.sendBody(directExampleRouteTest, testBody);

        toFileOutput.assertIsSatisfied();
    }
}
