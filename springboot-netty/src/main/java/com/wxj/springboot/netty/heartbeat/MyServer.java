package com.wxj.springboot.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * Netty 心跳检测机制案例
 * 实例要求:
 * 1) 编写一个 Netty 心跳检测机制案例, 当服务器超过 3 秒没有读时，就提示读空闲
 * 2) 当服务器超过 5 秒没有写操作时，就提示写空闲
 * 3) 实现当服务器超过 7 秒没有读或者写操作时，就提示读写空闲
 * @date 2022/3/29 0029 13:42
 */
public class MyServer {

    public static final int PORT = 7000;

    public static void main(String[] args) {


        //创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 逻辑代码块
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 拿到管道
                            ChannelPipeline pipeline = ch.pipeline();
                            // 加入一个Netty 提供的IdleStateHandler
                            /*
                                说明
                                1. IdleStateHandler 是 netty 提供的处理空闲状态的处理器
                                2. long readerIdleTime : 表示多长时间没有读, 就会发送一个心跳检测包检测是否连接
                                3. long writerIdleTime : 表示多长时间没有写, 就会发送一个心跳检测包检测是否连接
                                4. long allIdleTime : 表示多长时间没有读写, 就会发送一个心跳检测包检测是否连接
                                5. 文档说明
                                triggers an {@link IdleStateEvent} when a {@link Channel} has not performed
                                 read, write, or both operation for a while. * 6. 当 IdleStateEvent 触发后 , 就会传递给管道 的下一个 handler 去处理
                                 通过调用(触发)下一个 handler 的 userEventTiggered , 在该方法中去处理 IdleStateEvent(读
                                空闲，写空闲，读写空闲)
                             */
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            //加入一个对空闲检测进一步处理的 handler(自定义)
                            pipeline.addLast(new MyServerHandler());
                        }
                    });
            // 启动服务
            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 清理代码块
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}