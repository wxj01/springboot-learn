package com.wxj.springboot.mybatis.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Company.java
 * @Description 公司类
 * @createTime 2022年07月02日 13:02:00
 */
@Data
public class Company {

    private Long id ;
    private String name;
    private Integer personNum;
    private LocalDateTime createTime;
    private String creator;
    private LocalDateTime updateTime;
    private String updateUser;

    private List<Address> adds;

}
