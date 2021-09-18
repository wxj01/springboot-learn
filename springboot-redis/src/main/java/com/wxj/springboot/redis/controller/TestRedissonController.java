package com.wxj.springboot.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxj.springboot.redis.annotation.RedissonLockAnnotation;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName TestRedissonController.java
 * @Description TODO
 * @createTime 2021年09月17日 20:07:00
 */


@RestController
public class TestRedissonController {

    @Autowired
    private RedissonClient redissonClient;

    @PostMapping(value = "testLock", consumes = "application/json")
    @RedissonLockAnnotation(lockRedisKey = "productName,platFormName")
    public String testLock(@RequestBody JSONObject params) throws InterruptedException {
        /**
         * 分布式锁key=params.getString("productName")+params.getString("platFormName");
         * productName 产品名称  platFormName 平台名称 如果都一致,那么分布式锁的key就会一直,那么就能避免并发问题
         */
        //TODO 业务处理

        try {
            System.out.println("接收到的参数："+params.toString());
            System.out.println("执行相关业务...");
            System.out.println("执行相关业务.....");

            System.out.println("执行相关业务......");

        } catch (Exception e) {
            System.out.println("已进行日志记录");
        }

        return "success";
    }

    @GetMapping("/testData")
    public void testData(){

        //插入 字符串
        RBucket<String> keyObj = redissonClient.getBucket("keyStr");
        keyObj.set("testStr",300l, TimeUnit.SECONDS);

        //查询 字符串
        RBucket<String> keyGet = redissonClient.getBucket("keyStr");
        System.out.println(keyGet.get());
    }
}
