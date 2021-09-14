package com.wxj.springboot.jwt.service;


import com.wxj.springboot.jwt.entity.User;

import java.util.Date;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 15:10
 */
public interface TokenService {

    public String getToken(User user, Date date);
}
