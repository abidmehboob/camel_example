package org.example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:start")
                .routeId("greetings-route")
                .setBody(constant("Hello Baeldung Readers!"))
                .to("file:output");
    }
}
