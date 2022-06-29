package com.wxj.springboot.mybatis.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Employee.java
 * @Description TODO
 * @createTime 2022年05月27日 22:29:00
 */
@Data
@Alias("emp")
public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private String gender;
}
