/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-02
 */
package com.dotori.example.sqs.controller;

import com.dotori.example.sqs.constant.Queue;
import com.dotori.example.sqs.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class ExampleController {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public ExampleController(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @GetMapping("/send")
    public String send(@RequestParam Integer count) throws Exception{

        if(count == null) {
            return "fail";
        }

        if(count > 200) {
            return "fail";
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i = 0 ; i < count ; i ++) {
            UserDto userDto = UserDto.builder()
                    .name("joongHyun")
                    .age(i)
                    .createdTime(LocalDateTime.now())
                    .build();


            queueMessagingTemplate.convertAndSend(Queue.PORTAL_TEST, userDto);
        }
        stopWatch.stop();
        log.info("time : {} ms", stopWatch.getTotalTimeMillis());


        return "success";
    }
}