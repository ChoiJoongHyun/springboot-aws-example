package com.dotori.example.amazonmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringbootAmazonmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmazonmqApplication.class, args);
    }
}
