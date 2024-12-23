package camel.examples.camelsbexample.route;

import camel.examples.camelsbexample.bean.ExampleMessageTransformer;
import camel.examples.camelsbexample.routes.ExampleRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ExampleRouteTest {

    @Autowired
    private CamelContext camelContext;

    @MockitoBean
    private ExampleMessageTransformer transformerMock;

    private final String directTestEndpoint = "direct:example-route-test";

    @EndpointInject("mock:example-route-test")
    private MockEndpoint mock;

    @Produce(directTestEndpoint)
    private ProducerTemplate template;

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

        template.sendBodyAndHeader(testBody, "example-header", testHeader);

        verify(transformerMock, times(1)).transform(any());
        mock.assertIsSatisfied();
    }
}
