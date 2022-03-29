package com.wxj.springboot.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyServerInitializer.java
 * @Description TODO
 * @createTime 2022年03月29日 21:50:00
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyMessageDecoder()); //解码器
        pipeline.addLast(new MyMessageEncoder());// 编码器
        pipeline.addLast(new MyServerHandler());
    }
}
