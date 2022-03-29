package com.wxj.springboot.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 1) Netty 服务器在 6668 端口监听，客户端能发送消息给服务器 "hello, 服务器~"
 * 2) 服务器可以回复消息给客户端 "hello, 客户端~"
 * 3) 目的：对 Netty 线程模型 有一个初步认识, 便于理解 Netty 模型理论
 * 4) 看老师代码演示
 * 5.1 编写服务端 5.2 编写客户端 5.3 对 netty 程序进行分析，看看 netty 模型特点
 * 说明: 创建 Maven 项目，并引入 Netty 包
 * @date 2022/3/28 0028 16:41
 */
public class NettyServer {


    public static final int PORT = 6668;

    public static void main(String[] args) throws InterruptedException {
        //创建 BossGroup 和 WorkerGroup
        //说明
        //1. 创建两个线程组 bossGroup 和 workerGroup
        //2. bossGroup 只是处理连接请求 , 真正的和客户端业务处理，会交给 workerGroup 完成
        //3. 两个都是无限循环
        //4. bossGroup 和 workerGroup 含有的子线程(NioEventLoop)的个数
        // 默认实际 cpu 核数 * 2

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workderGroup = new NioEventLoopGroup();

        //创建服务器端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            //使用链式编程来进行设置
            bootstrap.group(bossGroup, workderGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用 NioSocketChannel 作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)// 设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道测试对象(匿名对象)
                        //给 pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new NettyServerHandler());
                            socketChannel.pipeline().addLast(new NettyServerHandler2());
                        }
                    });// 给我们的 workerGroup 的 EventLoop 对应的管道设置处理器
            System.out.println(".....服务器 is ready...");
            //绑定一个端口并且同步, 生成了一个 ChannelFuture 对象
            //启动服务器(并绑定端口)
            ChannelFuture cf = bootstrap.bind(PORT).sync();

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workderGroup.shutdownGracefully();
        }

    }
}