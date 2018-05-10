/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-02
 */
package com.dotori.example.sqs.listener;

import com.dotori.example.sqs.constant.Queue;
import com.dotori.example.sqs.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ExampleListener {

    /**
     * deletionPolicy = SqsMessageDeletionPolicy.NEVER
     *      로 진행할경우 acknowledgment.acknowledge() 를 해주어야지만 메세지가 성공적으로 수신되어 처리 되었다고 판단한다.
     * @SendTo 어노테이션으로 새로운 큐로 재 전송시 전달할 객체를 return 해주어야 한다. void 로 할경우 메세지 전달되지 않는다.
     * */
    @SqsListener(value = Queue.PORTAL_TEST, deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    @SendTo(value = Queue.PORTAL_TEST_B)
    public UserDto receive(@Payload UserDto userDto
            , @Headers Map<String, String> headers
            , @Header(name = "ReceiptHandle") String receiptHandle
            , Acknowledgment acknowledgment) throws Exception {

        log.info("threadId : {}, currentTime : {}", Thread.currentThread().getId(), System.currentTimeMillis());
        log.info("message : {}", userDto.toString());

        Thread.sleep(3000);

        acknowledgment.acknowledge();

        return userDto;
    }

    @MessageExceptionHandler(RuntimeException.class)
    public void messageExceptionHandler() {
        log.info("messageExceptionHandler error");
    }
}