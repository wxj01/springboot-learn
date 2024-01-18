package com.wxj.springboot.mybatis.controller;

import com.wxj.springboot.mybatis.domain.entity.User;
import com.wxj.springboot.mybatis.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2021年09月15日 20:32:00
 */
@RestController
class kUserController {

    @Resource
    UserServiceImpl userService;

    /**
     * 增加
     * @param userMap
     * @return
     */
    @PostMapping("/insertUserInfo")
    public String insertUserInfo(@RequestBody Map<String,String> userMap){
        User user = new User();
        user.setUserName(userMap.get("userName"));
        user.setUserAge(userMap.get("userAge"));
        boolean result = userService.insertUser(user);

        if(result){
            return "success";
        }else {
            return "fail";
        }
    }


    /**
     * 按id查找
     * @param id
     * @return
     */
    @GetMapping("/getUserInfoById/{id}")
    public String getUserInfoById(@PathVariable("id") int id){
        User user = userService.getUserInfoById(id);
        return user.toString();
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/queryUserInfo")
    public String getAllUserInfo(){
        List<User> users = userService.queryUserInfo();
        return users.toString();
    }

    /**
     * 更新
     * @param userMap
     * @return
     */
    @PostMapping("/updateUserInfoById")
    public String updateUserInfoById(@RequestBody Map<String,String> userMap){
        User user = new User();

        user.setId(Integer.valueOf(userMap.get("id")));
        user.setUserName(userMap.get("userName"));
        user.setUserAge(userMap.get("userAge"));
        boolean result = userService.updateUserInfoById(user);
        if(result){
            return "success";
        }else {
            return "fail";
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUserInfoById/{id}")
    public String deleteUserInfoById(@PathVariable("id") int id){

        boolean result = userService.deleteUserInfoById(id);
        if(result){
            return "success";
        }else {
            return "fail";
        }

    }
}
