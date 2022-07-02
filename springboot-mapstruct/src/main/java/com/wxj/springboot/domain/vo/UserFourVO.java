package com.wxj.springboot.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserVO4.java
 * @Description TODO
 * @createTime 2022年07月02日 23:05:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFourVO {
    // 实体类该属性名是id
    private String userId;
    // 实体类该属性名是name
    private String userName;
    private String createTime;
    private String updateTime;
}
