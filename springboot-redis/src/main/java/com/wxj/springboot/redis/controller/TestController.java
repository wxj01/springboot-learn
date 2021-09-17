package com.wxj.springboot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2021年09月17日 16:58:00
 */
@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("sendRedisMessageTest")
    public String SendRedisMessage(){
        System.out.println("Sending message ...");
        //第一个参数是，消息推送的主题名称；第二个参数是，要推送的消息信息
        //"chat"->主题
        //"我是一条消息"->要推送的消息
        stringRedisTemplate.convertAndSend("chat","我是一条消息");

        return "Send Success";
    }


}
