package com.wxj.springboot.netty.dubborpc.customer;

import com.wxj.springboot.netty.dubborpc.nettysomething.NettyClient;
import com.wxj.springboot.netty.dubborpc.publicinterface.HelloService;

import java.util.concurrent.TimeUnit;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/31 0031 13:47
 */
public class ClientBootstrap {

    //这里定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {
        //创建一个消费者
        NettyClient customer = new NettyClient();
        //创建代理对象
        HelloService helloService = (HelloService) customer.getBean(HelloService.class, providerName);

        for (;;){
            TimeUnit.SECONDS.sleep(2);
            //通过代理对象调用服务提供者的方法(服务)
            String msg = helloService.hello("你好 dubbo ~ ");
            System.out.println("调用的结果 res= " + msg);
        }
    }
}