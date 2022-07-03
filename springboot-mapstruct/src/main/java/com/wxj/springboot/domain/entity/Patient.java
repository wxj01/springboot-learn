package com.wxj.springboot.domain.entity;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Patient.java
 * @Description 患者类
 * @createTime 2022年07月03日 08:34:00
 */
@Data
public class Patient {
    private int id;
    private String name;
    private String dateOfBirth;
}
