package com.wxj.springboot.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyMessageDecoder.java
 * @Description TODO
 * @createTime 2022年03月29日 21:56:00
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyMessageDecoder decode 被调用");
        //需要将得到的二进制字节码 -》 MessageProtocol 数据包（对象）
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        //封装成 MessageProtocol对象，放入list，传递下一个handler业务处理
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(bytes);
        list.add(messageProtocol);

    }
}
