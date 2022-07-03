package com.wxj.springboot.domain.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName PatientDTO.java
 * @Description TODO
 * @createTime 2022年07月03日 08:36:00
 */
@Data
public class PatientDTO {
    private int id;
    private String name;
    //2022年7月3日09:16:19 新添加
    private LocalDate dateOfBirth;

}
