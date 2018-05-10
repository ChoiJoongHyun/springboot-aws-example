/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-02
 */
package com.dotori.example.sqs.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AwsSqsConfig {

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSqs) {
        return new QueueMessagingTemplate(amazonSqs);
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSqs);
        factory.setTaskExecutor(asyncTaskExecutor());

        factory.setAutoStartup(true);
        factory.setMaxNumberOfMessages(1);  //1 ~ 10까지만
        factory.setBackOffTime(5000L);

        return factory;
    }

    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("SimpleMessageListenerContainer-");

        threadPoolTaskExecutor.setCorePoolSize(2);  //5개 Pool Size 유지
        threadPoolTaskExecutor.setMaxPoolSize(3);  //최대 Pool Size
        // No use of a thread pool executor queue to avoid retaining message to long in memory
        threadPoolTaskExecutor.setQueueCapacity(0); //큐의 허용량 최대 MaxPoolSize 초과시 큐의 허용량.. 0으로 권장.
        threadPoolTaskExecutor.afterPropertiesSet();

        return threadPoolTaskExecutor;
    }
}