package com.wxj.springboot.controller;

import com.wxj.springboot.convert.UserCoverBasic;
import com.wxj.springboot.domain.entity.User;
import com.wxj.springboot.domain.vo.UserTwoVO;
import com.wxj.springboot.domain.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2022年07月02日 21:04:00
 */
@RestController
public class TestController {

    @GetMapping("/convert")
    public Object convertEntity() {
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
        return objectList;
    }
}
