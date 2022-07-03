package com.wxj.springboot.convert;

import com.wxj.springboot.domain.dto.PersonDTO;
import com.wxj.springboot.domain.entity.BasicEntity;
import com.wxj.springboot.domain.entity.Person;
import com.wxj.springboot.utils.DateTransform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import javax.xml.stream.events.StartDocument;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName PersonConvertBasic.java
 * @Description TODO
 * @createTime 2022年07月02日 22:13:00
 */
@Mapper(uses = DateTransform.class)
public interface PersonConvertBasic {

    PersonConvertBasic INSTANCE =Mappers.getMapper(PersonConvertBasic.class);

    /**
     * 转成 DTO
     * @param person
     * @return
     */
    @Mapping(target = "personName", source = "name")
    @Mapping(target = "id", ignore = true) // 忽略id，不进行映射
    PersonDTO conver(Person person);

    @Mapping(target = "personName",source = "name")
    PersonDTO conver2(Person person);

//    @Mapping(target = "createTime",source = "basicEntity.createTime")
//    PersonDTO combinationConver(Person personC, BasicEntity basicEntity);

    @Mapping(target = "id",source = "id")
    PersonDTO mapTo(Person person,String id);

}
