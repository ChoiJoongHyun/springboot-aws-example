/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-11
 */
package com.dotori.example.sns.controller;

import com.dotori.example.sns.constant.TopicName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SnsSendController {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public SnsSendController(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }


    //test
    @GetMapping("/send")
    public String send(@RequestParam Integer count){

        if(count == null) {
            return "fail";
        }

        if(count > 5) {
            return "fail";
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i = 0 ; i < count ; i ++) {
            this.notificationMessagingTemplate.sendNotification(TopicName.PORTAL_SNS_TEST, "message" + i, "subject" + i);
        }
        stopWatch.stop();
        log.info("time : {} ms", stopWatch.getTotalTimeMillis());

        return "success";
    }
}