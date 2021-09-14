package com.wxj.springboot.jwt.service;

import com.wxj.springboot.jwt.entity.User;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 14:14
 */
public interface UserInfoService {

    User getUserInfoById(Integer userId);

    User getUserInfoByName(String userName);

    int addUser(User user);
}
