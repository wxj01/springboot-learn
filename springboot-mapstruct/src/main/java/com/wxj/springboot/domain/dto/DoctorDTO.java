package com.wxj.springboot.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DoctorDTO.java
 * @Description TODO
 * @createTime 2022年07月03日 08:13:00
 */
@Data
public class DoctorDTO {
    private int id;
    private String name;
    private String degree;
    private String specialization;
    private List<PatientDTO> patientDTOList;
}
