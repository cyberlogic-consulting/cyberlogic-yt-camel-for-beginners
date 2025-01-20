package ch.cyberlogic.camel.examples.route;

public class ErrorHandlerExampleRoute extends ErrorHandlingExample {

    @Override
    public void configure() throws Exception {
        super.configure();

        from("timer:ticker?period=1s")
                .routeConfigurationId("example-error-handling")
                .setBody(simple("Hello World"))
                .doTry()
                .process(exchange -> {
                    throw new RuntimeException("Test exception");
                })
                .doCatch(Exception.class).onWhen(simple("${exception.message} contains 'Example'"))
                .log("Catch Exception: ${exception}")
                .doFinally()
                .log("Finally: ${exception}")
                .end()
                .log("Ended route with body: ${body}");
    }
}