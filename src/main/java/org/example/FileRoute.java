package org.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class FileRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("file:input")
                .process(exchange -> {
                    String originalFileContent = exchange.getIn().getBody(String.class);
                    String upperCaseContent = originalFileContent.toUpperCase();
                    exchange.getIn().setBody(upperCaseContent);
                })
                .to("file:output");
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.configure().addRoutesBuilder(new FileRoute());
        main.run(args);
    }
}
