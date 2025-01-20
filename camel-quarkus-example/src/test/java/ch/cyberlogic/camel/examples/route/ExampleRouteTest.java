package ch.cyberlogic.camel.examples.route;


import ch.cyberlogic.camel.examples.bean.ExampleMessageTransformer;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class ExampleRouteTest {

    @Inject
    CamelContext camelContext;

    @InjectMock
    ExampleMessageTransformer transformerMock;

    private final String directTestEndpoint = "direct:example-route-test";

    @EndpointInject("mock:examples-route-test")
    MockEndpoint mock;

    @Produce(directTestEndpoint)
    ProducerTemplate template;

    @BeforeEach
    public void replaceEndpoints() throws Exception {
        AdviceWith.adviceWith(
                camelContext,
                ExampleRoute.ID,
                route -> route
                        .interceptSendToEndpoint("jms:{{example.queue.output.name}}")
                        .skipSendToOriginalEndpoint()
                        .to(mock)
        );
        AdviceWith.adviceWith(
                camelContext,
                ExampleRoute.ID,
                route -> route.replaceFromWith(directTestEndpoint)
        );
    }

    @Test
    void testExampleRoute() throws Exception {
        String testBody = "test-body";
        String testHeader = "test-header";
        mock.expectedMessageCount(1);

        template.sendBodyAndHeader(testBody, "examples-header", testHeader);

        verify(transformerMock, times(1)).transform(any());
        mock.assertIsSatisfied();
    }
}
