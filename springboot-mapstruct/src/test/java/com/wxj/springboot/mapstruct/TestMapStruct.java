package com.wxj.springboot.mapstruct;

import com.wxj.springboot.convert.PersonConvertBasic;
import com.wxj.springboot.convert.UserCoverBasic;
import com.wxj.springboot.domain.dto.PersonDTO;
import com.wxj.springboot.domain.entity.Person;
import com.wxj.springboot.domain.entity.User;
import com.wxj.springboot.domain.vo.UserTwoVO;
import com.wxj.springboot.domain.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName TestMapStruct.java
 * @Description TODO
 * @createTime 2022年07月02日 21:36:00
 */
public class TestMapStruct {

    @Test
    public void test(){

        User user = User.builder()
                .id(1)
                .name("张三")
                .createTime("2022-7-2 21:06:42")
                .updateTime(LocalDateTime.now())
                .build();

        List<Object> objectList = new ArrayList<>();

        objectList.add(user);

        //使用mapstruct
        UserVO userVO = UserCoverBasic.INSTANCE.toConvertVo(user);
        objectList.add("userVO1:" + userVO);
        objectList.add("uservVO转回实体类user:"+ UserCoverBasic.INSTANCE.toConvertEntity(userVO));

        // 输出结果
        objectList.add("userVO2:" + " | " + UserCoverBasic.INSTANCE.toConvertVo2(user));
        //使用BeanUtils
        UserTwoVO userTwoVO = new UserTwoVO();
        BeanUtils.copyProperties(user,userTwoVO);
        objectList.add("BeanUtils userVO22:" + " | " + userTwoVO);

        System.out.println(objectList);
    }


    @Test
    public void test2(){
        Person person = new Person();
        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));


        PersonDTO personDTO = PersonConvertBasic.INSTANCE.conver(person);
        System.out.println(personDTO);
        // PersonDTO(describe=测试, id=null, personName=张三, age=18, source=100, height=170.5)
    }


}
