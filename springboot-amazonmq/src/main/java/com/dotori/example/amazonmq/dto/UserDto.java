/*
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   joonghyun                2018-05-16
 */
package com.dotori.example.amazonmq.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto implements Serializable {
    private String name;
    private Integer age;
    private LocalDateTime createdTime;
}