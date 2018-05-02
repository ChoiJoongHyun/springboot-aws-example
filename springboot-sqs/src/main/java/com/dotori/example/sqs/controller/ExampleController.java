/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-02
 */
package com.dotori.example.sqs.controller;

import com.dotori.example.sqs.constant.Queue;
import com.dotori.example.sqs.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ExampleController {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public ExampleController(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @GetMapping("/send")
    public String send() {
        UserDto userDto = UserDto.builder()
                .name("joongHyun")
                .age(31)
                .createdTime(LocalDateTime.now())
                .build();

        queueMessagingTemplate.convertAndSend(Queue.PORTAL_TEST, userDto);
        return "success";
    }
}