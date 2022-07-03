package com.wxj.springboot.domain.entity;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Education.java
 * @Description TODO
 * @createTime 2022年07月03日 08:12:00
 */
@Data
public class Education {

    private String degreeName;
    private String institute;
    private Integer yearOfPassing;
}
