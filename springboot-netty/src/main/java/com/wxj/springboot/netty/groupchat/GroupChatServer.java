package com.wxj.springboot.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * Netty 应用实例-群聊系统
 * 实例要求:
 * 1) 编写一个 Netty 群聊系统，实现服务器端和客户端之间的数据简单通讯（非阻塞）
 * 2) 实现多人群聊
 * 3) 服务器端：可以监测用户上线，离线，并实现消息转发功能
 * 4) 客户端：通过 channel 可以无阻塞发送消息给其它所有用户，同时可以接受其它用户发送的消息(有服务器转发
 * 得到)
 * 5) 目的：进一步理解 Netty 非阻塞网络编程机制
 * @date 2022/3/29 0029 11:13
 */
public class GroupChatServer {

    private int port ; //监听端口

    public GroupChatServer(int port) {
        this.port = port;
    }

    //编写 run 方法，处理客户端的请求
    public void run(){

        // 创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //获取到 pipeline
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //向 pipeline 加入解码器
                            pipeline.addLast("decoder",new StringDecoder());
                            //向 pipeline 加入编码器
                            pipeline.addLast("encoder",new StringEncoder());
                            //加入自己的业务处理 handler
                            pipeline.addLast(new GroupChatServerHandler());
                        }
                    });

            System.out.println("netty 服务器启动");

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            //监听关闭
            channelFuture.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 清理代码块
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new GroupChatServer(7000).run();
    }
}