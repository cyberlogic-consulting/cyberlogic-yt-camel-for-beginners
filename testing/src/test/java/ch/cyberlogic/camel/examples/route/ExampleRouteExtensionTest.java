package ch.cyberlogic.camel.examples.route;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.infra.core.CamelContextExtension;
import org.apache.camel.test.infra.core.DefaultCamelContextExtension;
import org.apache.camel.test.infra.core.annotations.ContextFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class ExampleRouteExtensionTest {

    @RegisterExtension
    protected static CamelContextExtension camelContextExtension = new DefaultCamelContextExtension();

    private static final String mockExampleRouteToFileOutput = "mock:exampleRouteToFileOutput";

    private static final String directExampleRouteTest = "direct:exampleRouteTest";

    @ContextFixture
    public void setUp(CamelContext camelContext) throws Exception {
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
    }

    @Test
    void testExampleRoute() throws Exception {
        String testBody = "Bye World";
        MockEndpoint toFileOutput = camelContextExtension.getMockEndpoint(mockExampleRouteToFileOutput);
        toFileOutput.expectedMessageCount(1);
        toFileOutput.message(0).body().isEqualTo(testBody.toUpperCase());
        ProducerTemplate producer = camelContextExtension.getProducerTemplate();

        producer.sendBody(directExampleRouteTest, testBody);

        toFileOutput.assertIsSatisfied();
    }
}
