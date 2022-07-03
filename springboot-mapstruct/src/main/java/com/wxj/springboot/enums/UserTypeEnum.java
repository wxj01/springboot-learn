package com.wxj.springboot.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserTypeEnum.java
 * @Description TODO
 * @createTime 2022年07月02日 23:15:00
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    Java("000","java开发工程师"),
    DB("001","数据库管理员"),
    LINUX("002","Linux运维");

    private String code;
    private String title;
}
