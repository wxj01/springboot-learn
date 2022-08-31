package com.wxj.springboot.elasticsearch02.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AhaIndex {

    private String name;
    private Integer age;
}
