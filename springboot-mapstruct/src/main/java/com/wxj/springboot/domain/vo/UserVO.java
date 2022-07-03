package com.wxj.springboot.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserVO.java
 * @Description 和 User实体类 属性一样
 * @createTime 2022年07月02日 20:46:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
    private Integer id;
    private String name;
    private String createTime;
    private LocalDateTime updateTime;
}
