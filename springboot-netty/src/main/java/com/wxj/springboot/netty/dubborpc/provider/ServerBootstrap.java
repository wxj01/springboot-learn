package com.wxj.springboot.netty.dubborpc.provider;

import com.wxj.springboot.netty.dubborpc.nettysomething.NettyServer;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO  ServerBootstrap 会启动一个服务提供者，就是 NettyServer
 * @date 2022/3/31 0031 13:31
 */
public class ServerBootstrap {

    public static final String SERVER_IP = "127.0.0.1";
    public static final int PORT = 7000;

    public static void main(String[] args) {
        //代码代填..

        NettyServer.startServer(SERVER_IP,PORT);
    }
}