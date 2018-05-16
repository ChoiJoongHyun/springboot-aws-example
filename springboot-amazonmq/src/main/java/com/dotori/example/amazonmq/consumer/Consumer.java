/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-14
 */
package com.dotori.example.amazonmq.consumer;

import com.dotori.example.amazonmq.constant.Queue;
import com.dotori.example.amazonmq.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @JmsListener(destination = Queue.PORTAL_TEST)
    public void receiveQueue(UserDto userDto) throws Exception {
        log.info("user info : {}", userDto.toString());
        Thread.sleep(10000);
    }
}