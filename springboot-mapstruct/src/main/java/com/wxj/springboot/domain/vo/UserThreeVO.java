package com.wxj.springboot.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserThreeVO.java
 * @Description TODO
 * @createTime 2022年07月02日 22:48:00
 */
@Data
public class UserThreeVO {
    private Integer id;
    private String name;
    // 实体类该属性是String 这里用 LocalDateTime类型
    private LocalDateTime createTime;
    // 实体类该属性是LocalDateTime 这里用String
    private String  updateTime;
}
