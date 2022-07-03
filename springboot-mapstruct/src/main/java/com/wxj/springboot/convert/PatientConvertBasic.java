package com.wxj.springboot.convert;

import com.wxj.springboot.domain.dto.DoctorDTO;
import com.wxj.springboot.domain.dto.PatientDTO;
import com.wxj.springboot.domain.entity.Doctor;
import com.wxj.springboot.domain.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import javax.print.Doc;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName PatientConvertBasic.java
 * @Description TODO
 * @createTime 2022年07月03日 08:38:00
 */
@Mapper
public interface PatientConvertBasic {

    PatientConvertBasic INSTANCE = Mappers.getMapper(PatientConvertBasic.class);

    /**
     *  转成DTO
     * @param patient
     * @return
     */
    PatientDTO toDto(Patient patient);


    /**
     *  将dto的LocalDate 转成 实体的String
     * @param patientDTO
     * @return
     */
    @Mapping(source = "dateOfBirth",target = "dateOfBirth",dateFormat = "dd/MMM/yyyy")
    Patient toModel(PatientDTO patientDTO);

}
