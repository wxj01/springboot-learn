package com.wxj.springboot.netty.protocoltcp;

import com.wxj.springboot.netty.tcp.MyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyServer.java
 * @Description TODO
 *
 * @createTime 2022年03月29日 19:51:00
 */
public class MyServer {

    public static final int PORT = 7000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //逻辑代码
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer()) ;//自定义一个初始化类

            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            //异常处理代码
        } finally {
            //一定要执行的代码
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
