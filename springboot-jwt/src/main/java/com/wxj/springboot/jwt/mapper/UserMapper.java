package com.wxj.springboot.jwt.mapper;

import com.wxj.springboot.jwt.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 14:46
 */
@Mapper
public interface UserMapper {

    @Select("select * from user_info where UI_ID=#{userId}")
    User getUserInfoById(@Param("userId") Integer userId);

    @Select("select * from user_info where UI_USER_NAME=#{userName}")
    User getUserInfoByName(@Param("userName") String userName);

    @Insert("insert into user_info (UI_USER_NAME, UI_PASSWORD, UI_STATUS,UI_CREATE_TIME, UI_ROLES) " +
            " VALUES ( #{UI_USER_NAME}, #{UI_PASSWORD},#{UI_STATUS},#{UI_CREATE_TIME},#{UI_ROLES}) ")
    @Options(useGeneratedKeys = true,keyProperty = "UI_ID")
    int addUser(User user);
}
