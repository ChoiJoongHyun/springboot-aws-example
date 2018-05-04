package com.dotori.example.sqs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringbootSqsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringbootSqsApplication.class)
            .properties(
                "spring.config.additional-location="
                    + "file:\\C:\\Users\\joonghyun\\Documents\\config\\application.yml"

            )
            .run(args);
    }
}
