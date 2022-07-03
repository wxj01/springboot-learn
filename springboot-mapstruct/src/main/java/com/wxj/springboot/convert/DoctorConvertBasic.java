package com.wxj.springboot.convert;

import com.wxj.springboot.domain.dto.DoctorDTO;
import com.wxj.springboot.domain.entity.Doctor;
import com.wxj.springboot.domain.entity.Education;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DoctorConvertBasic.java
 * @Description TODO
 * @createTime 2022年07月03日 08:21:00
 */
@Mapper(uses = {PatientConvertBasic.class})
public interface DoctorConvertBasic {

    DoctorConvertBasic INSTANCE = Mappers.getMapper(DoctorConvertBasic.class);

    /**
     * doctor 转成 dto
     * @param doctor
     * @return
     */
    @Mapping(source = "specialty",target = "specialization")
    DoctorDTO toDto(Doctor doctor);


    /**
     * 多个源类 处理
     * @param doctor
     * @param education
     * @return
     */
//    @Mappings()
    @Mapping(source = "doctor.specialty",target = "specialization")
    @Mapping(source = "education.degreeName",target = "degree")
    DoctorDTO toDTO(Doctor doctor, Education education);


    /**
     *  添加了一个list 转成dto
     * @param doctor
     * @return
     */
//    @Mappings(

//    )
    @Mapping(source = "doctor.patientList",target = "patientDTOList")
    @Mapping(source = "doctor.specialty",target = "specialization")
    DoctorDTO toDto2(Doctor doctor);

//    @Mappings()
    @Mapping(source = "doctorDTO.specialization",target = "specialty")
    @Mapping(source = "doctorDTO.patientDTOList",target = "patientList")
    void updateModel(DoctorDTO doctorDTO, @MappingTarget Doctor doctor);
}
