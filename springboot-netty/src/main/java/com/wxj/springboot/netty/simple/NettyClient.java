package com.wxj.springboot.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/29 0029 8:43
 */
public class NettyClient {

    public static final int PORT = 6668;

    public static void main(String[] args) {

        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();

            //设置相关参数
            bootstrap.group(group)//设置线程组
                    .channel(NioSocketChannel.class) // 设置客户端通道的实现类(反射)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler()); //加入自己的处理器 NettyClientHandler
                        }
                    });

            System.out.println("客服端 OK ...");
            //启动客户端去连接服务器端
            //关于 ChannelFuture 要分析，涉及到 netty 的异步模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", PORT).sync();
            //给关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
            // 可能会发生异常的语句
        } catch(Exception e) {
            // 处理异常语句
            e.printStackTrace();
        } finally {
            // 清理代码块
            group.shutdownGracefully();
        }
    }
}