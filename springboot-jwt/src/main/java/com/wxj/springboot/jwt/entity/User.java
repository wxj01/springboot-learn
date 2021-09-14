package com.wxj.springboot.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer UI_ID;
    private String UI_USER_NAME;
    private String UI_PASSWORD;
    private String UI_STATUS;
    private Long UI_CREATE_TIME;
    private String UI_ROLES;


}