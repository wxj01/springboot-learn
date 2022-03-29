package com.wxj.springboot.netty.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/29 0029 16:23
 */
public class NettyClient {

    public static final int PORT = 6669;

    public static void main(String[] args) {
        //客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 设置客户端通道的实现类(反射)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //在pipeline中加入 ProtoBufEncoder
                            pipeline.addLast("encoder",new ProtobufEncoder());
                            pipeline.addLast(new NettyClientHandler()); //加入自己的处理器
                        }
                    });
            System.out.println("客服端。。。连接");
            ChannelFuture channelFuture = bootstrap.bind("127.0.0.1", PORT).sync();
            //给关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
            // 可能会发生异常的语句
        } catch(Exception e) {
            // 处理异常语句
        } finally {
            // 清理代码块
            group.shutdownGracefully();
        }
    }


}