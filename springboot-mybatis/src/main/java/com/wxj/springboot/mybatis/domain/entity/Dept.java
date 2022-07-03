package com.wxj.springboot.mybatis.domain.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Dept.java
 * @Description TODO
 * @createTime 2022年07月02日 13:07:00
 */
@Data
public class Dept {

    private Long did;

    private String deptName;

    private List<Employee> emps;
}
