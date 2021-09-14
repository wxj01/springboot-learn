package com.wxj.springboot.jwt.controller;


import com.wxj.springboot.jwt.annotation.CheckToken;
import com.wxj.springboot.jwt.annotation.PassToken;
import com.wxj.springboot.jwt.entity.User;
import com.wxj.springboot.jwt.service.TokenService;
import com.wxj.springboot.jwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 0014 15:15
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @Resource
    TokenService tokenService;

    @Value("${EXPIRE_TIME}")
    private String EXPIRE_TIME;


    @GetMapping("/getUserByName/{userName}")
    public String getUser(@RequestParam("userName") String userName){
        User userInfoByName = userInfoService.getUserInfoByName(userName);
        return userInfoByName.toString();
    }
    
    
    /** 
     * @description: 注册
     * @param: * @param: map 
     * @return: java.lang.String 
     * @author wangxinjian
     * @date: 2021/9/14 0014 15:24
     */ 
    @PassToken
    @PostMapping("/register")
    public String register(@RequestParam Map map){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePwd  = bCryptPasswordEncoder.encode(String.valueOf(map.get("password")));

        User user = new User();
        user.setUI_USER_NAME(String.valueOf(map.get("username")));
        user.setUI_PASSWORD(encodePwd);
        user.setUI_STATUS("0");
        user.setUI_CREATE_TIME(System.currentTimeMillis());
        user.setUI_ROLES(String.valueOf(map.get("roles")));

        int i = userInfoService.addUser(user);
        if(i == 1){
            return "注册成功";
        }else {
            return "注册失败";
        }
    }


    /**
     * @description: 登录
     * @param: * @param: user
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @author wangxinjian
     * @date: 2021/9/14 0014 15:32
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestParam Map user){
        Map result=new HashMap();
        User userForBase=userInfoService.getUserInfoByName(String.valueOf(user.get("username")));

        if(userForBase == null){
            result.put("message","登录失败,用户不存在");
            return result;
        }else {
            Date expiresDate = new Date(System.currentTimeMillis()+Integer.valueOf(EXPIRE_TIME)*60*1000);
            String token = tokenService.getToken(userForBase,expiresDate);

            result.put("token", token);
            result.put("expireTime", EXPIRE_TIME);
            result.put("userId", userForBase.getUI_ID());

            return result;
        }

    }


    @CheckToken
    @GetMapping("/afterLogin")
    public String afterLogin(){
        return "你已通过验证,成功进入系统";
    }
    
}