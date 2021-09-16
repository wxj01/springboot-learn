package com.wxj.springboot.webflux.pojo;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年09月16日 22:51:00
 */

@Data
public class User {
    /**
     * id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
}
