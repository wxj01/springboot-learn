package com.wxj.springboot.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyServerHandler.java
 * @Description TODO
 * @createTime 2022年03月29日 22:08:00
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //接收到数据，并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("服务器接收到信息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, Charset.forName("utf-8")));


        //回复消息
        String responseContent = UUID.randomUUID().toString();
        int length = responseContent.getBytes(Charset.forName("utf-8")).length;
        byte[] bytes = responseContent.getBytes(Charset.forName("utf-8"));

        //构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(bytes);

        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
