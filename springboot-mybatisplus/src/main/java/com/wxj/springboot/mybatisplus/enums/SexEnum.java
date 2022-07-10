package com.wxj.springboot.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    //将注解所标识的属性的值存储到数据库中
    @EnumValue
    private int sex;
    private String sexName;
}
