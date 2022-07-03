package com.wxj.springboot.mybatis.domain.vo;

import com.wxj.springboot.mybatis.domain.entity.Address;
import com.wxj.springboot.mybatis.domain.entity.Company;
import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserVO.java
 * @Description  用户vo
 * @createTime 2022年07月02日 12:58:00
 */
@Data
public class UserVO {

    private String userName;
    private Integer userAge;
    private Company company;
    private Address address;


}
