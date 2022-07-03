package com.wxj.springboot.domain.dto;

import com.wxj.springboot.domain.entity.Child;
import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName PersonDTO.java
 * @Description TODO
 * @createTime 2022年07月02日 22:00:00
 */
@Data
public class PersonDTO {

    String describe;

    private Long id;

    private String personName;

    private String age;

    private String source;

    private String height;

    private Child child;
}
