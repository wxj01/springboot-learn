package com.wxj.springboot.jta.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年09月14日 21:38:00
 */

@Data
@ToString
public class User {
    private Integer id;
    private String username;
    private Integer age;
}
