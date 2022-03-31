package com.wxj.springboot.netty.dubborpc.nettysomething;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/31 0031 13:52
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {


    private ChannelHandlerContext context; // 上下文
    private String result; //返回的结果
    private String param; // 客服端调用方法时，传入的参数
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //与服务器的连接创建后，就会被调用, 这个方法是第一个被调用(1)
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" channelActive 被调用 ");
        this.context = ctx;
    }

    //收到服务器的数据后，调用方法 (4)
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" channelRead 被调用 ");
        result = msg.toString();
        notify(); //唤醒等待的线程
    }

//    @Override
//    public  void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        lock.lock();
//        try {
//            // 逻辑代码块
//            System.out.println(" channelRead 被调用 ");
//            result = msg.toString();
//            condition.signalAll(); //唤醒等待的线程
//        } finally {
//            // 清理代码块
//            lock.unlock();
//        }
//
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    //被代理对象调用, 发送数据给服务器，-> wait -> 等待被唤醒(channelRead) -> 返回结果 (3)-》5
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("wait 之前 call 被调用 ");
        context.writeAndFlush(param);
        //进行wait
        wait(); //等待 channelRead 方法获取到服务器的结果后，唤醒
        System.out.println("wait 之后 call 被调用 ");
        return result;
    }


//    @Override
//    public Object call() throws Exception {
//        lock.lock();
//        try {
//            // 逻辑代码块
//            System.out.println("wait 之前 call 被调用 ");
//            context.writeAndFlush(context);
//            //进行wait
//            condition.await(); //等待 channelRead 方法获取到服务器的结果后，唤醒
//            System.out.println("wait 之后 call 被调用 ");
//            return result;
//        } finally {
//            // 清理代码块
//            lock.unlock();
//        }
//
//    }

    //设置参数  （2）
    public void setParam(String param) {
        this.param = param;
    }
}