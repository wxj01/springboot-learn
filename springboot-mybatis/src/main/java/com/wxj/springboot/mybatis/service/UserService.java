package com.wxj.springboot.mybatis.service;

import com.wxj.springboot.mybatis.domain.entity.User;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年09月15日 20:05:00
 */
public interface UserService {

    //添加
    boolean insertUser(User user);

    //按照id 查找
    User getUserInfoById(int id);

    //查询所有
    List<User> queryUserInfo();

    //更新
    boolean updateUserInfoById(User user);

    //删除
    boolean deleteUserInfoById(int id);
}
