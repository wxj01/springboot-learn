package com.wxj.springboot.netty.dubborpc.provider;

import com.wxj.springboot.netty.dubborpc.publicinterface.HelloService;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/31 0031 13:27
 */
public class HelloServiceImpl implements HelloService {

    private static int count = 0;

    //当有消费方调用该方法时，就返回一个结果
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息=" + msg);

        //根据msg 的 返回不同的结果
        if (msg != null){
            return "你好客户端, 我已经收到你的消息 [" + msg + "] 第" + (++count) + " 次";
        }else{
            return "你好客户端, 我已经收到你的消息 ";
        }

    }
}