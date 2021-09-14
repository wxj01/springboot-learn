package com.wxj.springboot.jta.controller;

import com.wxj.springboot.jta.pojo.User;
import com.wxj.springboot.jta.service.TestJtaService;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2021年09月14日 21:49:00
 */
@RestController
public class TestController {


    @Resource
    TestJtaService jtaService;

    @GetMapping("/testInsertOne")
    @Transactional
    public String testInsertOne(){
        User user = new User();
        user.setUsername("testUserOne");
        user.setAge(10);

        try{
            jtaService.testInsertUser(user);
        }catch (Exception e){
            System.out.println("触发事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return "ok";
    }

    @GetMapping("/testInsertTwo")
    @Transactional
    public String testInsertTwo(){
        User user = new User();
        user.setUsername("testUserTwo");
        user.setAge(10);

        try{
            jtaService.testInsertUser2(user);
            int num = 100/0;
        }catch (Exception e){
            System.out.println("触发事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "ok";
    }
}
