package com.wxj.springboot.netty.dubborpc.publicinterface;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO //这个是接口，是服务提供方和 服务消费方都需要
 * @date 2022/3/31 0031 13:26
 */
public interface HelloService {

    String hello(String msg);
}