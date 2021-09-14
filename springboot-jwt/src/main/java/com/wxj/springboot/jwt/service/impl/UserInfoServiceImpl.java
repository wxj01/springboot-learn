package com.wxj.springboot.jwt.service.impl;

import com.wxj.springboot.jwt.entity.User;
import com.wxj.springboot.jwt.service.UserInfoService;
import com.wxj.springboot.jwt.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 14:15
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getUserInfoById(Integer userId) {
        return userMapper.getUserInfoById(userId);
    }

    @Override
    public User getUserInfoByName(String userName) {
        return userMapper.getUserInfoByName(userName);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}