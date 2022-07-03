package com.wxj.springboot.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName BasicEntity.java
 * @Description 多个源对象
 * @createTime 2022年07月03日 07:43:00
 */
@Data
public class BasicEntity {

    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private int _ROW;
}
