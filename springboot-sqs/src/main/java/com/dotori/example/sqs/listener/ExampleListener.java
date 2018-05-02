/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-02
 */
package com.dotori.example.sqs.listener;

import com.dotori.example.sqs.constant.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ExampleListener {

    @SqsListener(value = Queue.PORTAL_TEST)
    public void receive(@Headers Map<String, String> headers, String message) {
        log.info("message : {}", message);
        log.info("headers : {}", headers.toString());
    }
}