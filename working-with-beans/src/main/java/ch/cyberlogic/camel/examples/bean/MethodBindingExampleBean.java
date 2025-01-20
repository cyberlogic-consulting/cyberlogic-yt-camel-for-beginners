package ch.cyberlogic.camel.examples.bean;

import org.apache.camel.Body;
import org.apache.camel.Handler;

public class MethodBindingExampleBean {

    @Handler
    public String transform(@Body String body) {
        return body.replace("hello", "bye");
    }

}
