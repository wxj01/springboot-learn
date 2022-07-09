package com.wxj.springboot.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Customer.java
 * @Description TODO
 * @createTime 2022年06月29日 22:37:00
 */
@Data
@TableName("customer")
public class Customer {

    @TableId(type = IdType.AUTO)
    private Long id;
    //如果字段和数据库中的对应可省 该注解
//    @TableField(value = "NAME",exist = true)
    private String customerName;
    private String gender;
    private String telephone;
    private String registerTime;
}
