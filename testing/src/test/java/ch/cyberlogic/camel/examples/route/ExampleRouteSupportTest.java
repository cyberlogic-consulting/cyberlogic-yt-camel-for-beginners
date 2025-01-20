package ch.cyberlogic.camel.examples.route;

import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ExampleRouteSupportTest extends CamelTestSupport {

    private static final String mockExampleRouteToFileOutput = "mock:exampleRouteToFileOutput";

    private static final String directExampleRouteTest = "direct:exampleRouteTest";

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        super.beforeTestExecution(context);

        AdviceWith.adviceWith(
                context(),
                ExampleRoute.ROUTE_ID,
                route -> route
                        .interceptSendToEndpoint("file:src/main/resources/files/output")
                        .skipSendToOriginalEndpoint()
                        .to(mockExampleRouteToFileOutput)
        );
        AdviceWith.adviceWith(
                context(),
                ExampleRoute.ROUTE_ID,
                route -> route
                        .replaceFromWith(directExampleRouteTest)
        );
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new ExampleRoute();
    }

    @Test
    void testExampleRoute() throws Exception {
        String testBody = "Bye World";
        MockEndpoint toFileOutput = getMockEndpoint(mockExampleRouteToFileOutput);
        toFileOutput.expectedMessageCount(1);
        toFileOutput.message(0).body().isEqualTo(testBody.toUpperCase());

        template().sendBody(directExampleRouteTest, testBody);

        toFileOutput.assertIsSatisfied();
    }
}
