package com.wxj.springboot.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/28 0028 16:53
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //读取数据实际(这里我们可以读取客户端发送的消息)
    /*
    1. ChannelHandlerContext ctx:上下文对象, 含有 管道 pipeline , 通道 channel, 地址
    2. Object msg: 就是客户端发送的数据 默认 Object
    */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程:"+Thread.currentThread().getName());
        System.out.println("server ctx :"+ ctx);
        System.out.println("看看 channel 和 pipeline 的关系");

        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        //将 msg 转成一个 ByteBuf
        //ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer.
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送消息是：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址："+ channel.remoteAddress());
    }

    //数据读取完毕

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵",CharsetUtil.UTF_8));
    }

    //处理异常, 一般是需要关闭通道

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}