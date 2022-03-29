package com.wxj.springboot.netty.protocoltcp;

import com.wxj.springboot.netty.tcp.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyClient.java
 * @Description TODO
 * @createTime 2022年03月29日 22:15:00
 */
public class MyClient {

    public static final int PORT = 7000;

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            //逻辑代码

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer()); //自定义一个初始化类

            ChannelFuture channelFuture = bootstrap.bind("127.0.0.1", PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            //异常处理代码
        } finally {
            //一定要执行的代码
            group.shutdownGracefully();
        }
    }

}
