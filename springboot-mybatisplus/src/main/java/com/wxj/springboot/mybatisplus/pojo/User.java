package com.wxj.springboot.mybatisplus.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.wxj.springboot.mybatisplus.enums.SexEnum;
import lombok.Data;


@Data
//@TableName("t_user")
public class User {

    @TableId("uid")
    private Long uid;

    @TableField("username")
    private String name;

    private Integer age;
    private String email;

    @TableLogic
    private  int isDeleted;

    private SexEnum sex;
}

