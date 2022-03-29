package com.wxj.springboot.netty.protocoltcp;

import com.wxj.springboot.netty.tcp.MyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyClientInitializer.java
 * @Description TODO
 * @createTime 2022年03月29日 22:29:00
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyMessageEncoder()); //加入编码器
        pipeline.addLast(new MyMessageDecoder()); //加入解码器
        pipeline.addLast(new MyClientHandler());
    }
}
