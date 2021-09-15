package com.wxj.springboot.mybatis.mapper;

import com.wxj.springboot.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2021年09月15日 19:48:00
 */
@Mapper
public interface UserMapper {

    /**
     * 添加user信息
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据ID查询经销商信息
     * @param id
     * @return
     */
    User getUserInfoById(int id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> queryUserInfo();

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUserInfoById(User user);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    int deleteUserInfoById(int id);
}
