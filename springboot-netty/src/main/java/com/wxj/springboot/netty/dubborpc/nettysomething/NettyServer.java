package com.wxj.springboot.netty.dubborpc.nettysomething;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
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
 * @date 2022/3/31 0031 13:34
 */
public class NettyServer {


    public static void startServer(String serverIp, int port) {
        startServer0(serverIp,port);
    }


    //编写一个方法，完成对 NettyServer 的初始化和启动
    private static void startServer0(String serverIp, int port) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            //添加编码、解码
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new StringDecoder());
                            //添加自定义的handler
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(serverIp, port).sync();
            System.out.println("服务提供方开始提供服务~~");
            channelFuture.channel().closeFuture().sync();
            // 可能会发生异常的语句
        } catch(Exception e) {
            // 处理异常语句
        } finally {
            // 清理代码块
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}