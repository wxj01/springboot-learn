package com.wxj.springboot.domain.entity;

import com.wxj.springboot.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserEnum.java
 * @Description TODO
 * @createTime 2022年07月02日 23:19:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEnum {
    private Integer id;
    private String name;
    private UserTypeEnum userTypeEnum;
}
