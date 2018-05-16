/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-14
 */
package com.dotori.example.amazonmq.controller;

import com.dotori.example.amazonmq.constant.Queue;
import com.dotori.example.amazonmq.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class SendController {


    private final JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    public SendController(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    //test
    @GetMapping("/send")
    public String send(@RequestParam Integer count){

        if(count == null || count > 200) {
            return "fail";
        }

        StopWatch totalStopWatch = new StopWatch();
        totalStopWatch.start();
        for(int i = 0 ; i < count ; i ++) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            UserDto userDto = UserDto.builder()
                    .name("joongHyun")
                    .age(i)
                    .createdTime(LocalDateTime.now())
                    .build();

            this.jmsMessagingTemplate.convertAndSend(Queue.PORTAL_TEST, userDto);
            stopWatch.stop();
            log.info("{} time : {} ms",i ,stopWatch.getTotalTimeMillis());
        }
        totalStopWatch.stop();
        log.info("total time : {} ms", totalStopWatch.getTotalTimeMillis());

        return "success";
    }
}