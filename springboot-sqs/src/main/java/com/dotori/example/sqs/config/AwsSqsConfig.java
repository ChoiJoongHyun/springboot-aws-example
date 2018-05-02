/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-02
 */
package com.dotori.example.sqs.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSqsConfig {

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSqs) {
        return new QueueMessagingTemplate(amazonSqs);
    }
}