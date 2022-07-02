package com.wxj.springboot.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserVO.java
 * @Description 和 User 少一个属性 updateTime
 * @createTime 2022年07月02日 20:46:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTwoVO {
    private Integer id;
    private String name;
    private String createTime;
}
