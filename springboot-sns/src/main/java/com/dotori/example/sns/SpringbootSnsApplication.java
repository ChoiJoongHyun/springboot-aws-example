package com.dotori.example.sns;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringbootSnsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringbootSnsApplication.class)
                .properties(
                        "spring.config.additional-location="
                                + "file:\\C:\\Users\\joonghyun\\Documents\\config\\application.yml"

                )
                .run(args);
    }
}
