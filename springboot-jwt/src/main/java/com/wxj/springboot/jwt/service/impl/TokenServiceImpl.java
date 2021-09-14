package com.wxj.springboot.jwt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wxj.springboot.jwt.entity.User;
import com.wxj.springboot.jwt.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 15:08
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user, Date date) {
        String token = JWT.create()
                .withAudience(String.valueOf(user.getUI_ID()))
                .withExpiresAt(date) // 过期时间配置
                .sign(Algorithm.HMAC256(user.getUI_PASSWORD()));

        return token;
    }
}