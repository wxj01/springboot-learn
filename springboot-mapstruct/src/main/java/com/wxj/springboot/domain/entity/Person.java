package com.wxj.springboot.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Person.java
 * @Description TODO
 * @createTime 2022年07月02日 21:59:00
 */
@Data
public class Person {

    String describe;

    private String id;

    private String name;

    private int age;

    private BigDecimal source;

    private double height;

    private Date createTime;
}
