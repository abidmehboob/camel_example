package org.example;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelDemoApplication.class, args);
    }

    @Bean
    public RouteBuilder routeBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                // Example Route
                from("timer:foo?period=1000") // Generate messages every second
                        .routeId("example-route")
                        .setBody().simple("Hello Camel at ${date:now:yyyy-MM-dd HH:mm:ss}")
                        .log("${body}");
            }
        };
    }
}