package com.wxj.springboot.netty.dubborpc.nettysomething;

import com.wxj.springboot.netty.dubborpc.customer.ClientBootstrap;
import com.wxj.springboot.netty.dubborpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO 服务器这边 handler 比较简单
 * @date 2022/3/31 0031 13:42
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送的消息，并调用服务
        System.out.println("msg=" + msg);
        //客户端在调用服务器的 api 时，我们需要定义一个协议
        //比如我们要求 每次发消息是都必须以某个字符串开头 "HelloService#hello#你好"
        if (msg.toString().startsWith(ClientBootstrap.providerName)){
            String result = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#")
                    + 1));
            ctx.writeAndFlush(result);

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}