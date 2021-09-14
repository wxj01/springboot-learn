package com.wxj.springboot.jta.mapper;

import com.wxj.springboot.jta.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2021年09月14日 21:41:00
 */
@Mapper
public interface UserMapper {

    int insert(User user);
}
