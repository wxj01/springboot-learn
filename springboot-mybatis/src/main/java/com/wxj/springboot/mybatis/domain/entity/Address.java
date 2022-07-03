package com.wxj.springboot.mybatis.domain.entity;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Address.java
 * @Description 地址类
 * @createTime 2022年07月02日 13:03:00
 */
@Data
public class Address {

    private Long addressId;
    private Long companyId;
    private String province;
    private String city;
    private String detailAddress;
}
