package com.wxj.springboot.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyMessageEncoder.java
 * @Description TODO
 * @createTime 2022年03月29日 22:05:00
 */
public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx,
                          MessageProtocol message,
                          ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder encode 方法被调用");
        out.writeInt(message.getLen());
        out.writeBytes(message.getContent());
    }
}
