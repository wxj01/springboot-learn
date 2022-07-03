package com.wxj.springboot.mybatis.domain.entity;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Employee.java
 * @Description TODO
 * @createTime 2022年05月27日 22:29:00
 */
@Data
public class Employee {

    private Integer eid;

    private String empName;

    private Integer age;

    private String sex;

    private String email;

    private Dept dept;
}
