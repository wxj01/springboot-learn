package com.wxj.springboot.domain.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Doctor.java
 * @Description TODO
 * @createTime 2022年07月03日 08:18:00
 */
@Data
public class Doctor {
    private int id;
    private String name;
    private String specialty;
    private List<Patient> patientList;
}
