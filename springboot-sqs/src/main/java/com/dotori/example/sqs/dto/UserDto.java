/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-02
 */
package com.dotori.example.sqs.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDto {
    private String name;
    private Integer age;
    private LocalDateTime createdTime;
}