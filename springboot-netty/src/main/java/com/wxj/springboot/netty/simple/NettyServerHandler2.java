package com.wxj.springboot.netty.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 任务队列中的 Task 有 3 种典型使用场景
 * 1) 用户程序自定义的普通任务 [举例说明]
 * 2) 用户自定义定时任务
 * 3) 非当前 Reactor 线程调用 Channel 的各种方法
 * 例如在推送系统的业务线程里面，根据用户的标识，找到对应的 Channel 引用，然后调用 Write 类方法向该
 * 用户推送消息，就会进入到这种场景。最终的 Write 会提交到任务队列中后被异步消费
 *
 *
 * 说明
 * 1. 我们自定义一个 Handler 需要继续 netty 规定好的某个 HandlerAdapter(规范)
 * 2. 这时我们自定义一个 Handler , 才能称为一个 handler
 *
 * @date 2022/3/28 0028 16:53
 */
public class NettyServerHandler2 extends ChannelInboundHandlerAdapter {
    //读取数据实际(这里我们可以读取客户端发送的消息)
    /*
    1. ChannelHandlerContext ctx:上下文对象, 含有 管道 pipeline , 通道 channel, 地址
    2. Object msg: 就是客户端发送的数据 默认 Object
    */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //比如这里我们有一个非常耗时长的业务-> 异步执行 -> 提交该 channel 对应的
        //NIOEventLoop 的 taskQueue 中,

        //解决方案 1  用户程序自定义的普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);

                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 2", CharsetUtil.UTF_8));
                    System.out.println("channel code ="+ ctx.channel().hashCode());
                    // 可能会发生异常的语句
                } catch(Exception e) {
                    // 处理异常语句
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        });

        //再提交一个耗时的任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);

                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 3", CharsetUtil.UTF_8));
                    System.out.println("channel code ="+ ctx.channel().hashCode());
                    // 可能会发生异常的语句
                } catch(Exception e) {
                    // 处理异常语句
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        });


        //解决方案 2 : 用户自定义定时任务 -》 该任务是提交到 scheduledTaskQueue 中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);

                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 3", CharsetUtil.UTF_8));
                    System.out.println("channel code ="+ ctx.channel().hashCode());
                    // 可能会发生异常的语句
                } catch(Exception e) {
                    // 处理异常语句
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        },5,TimeUnit.SECONDS);

        System.out.println("go on ...");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //        super.channelReadComplete(ctx);
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}