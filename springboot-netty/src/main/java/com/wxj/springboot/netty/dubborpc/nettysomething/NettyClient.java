package com.wxj.springboot.netty.dubborpc.nettysomething;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/31 0031 13:51
 */
public class NettyClient {

    public static final String SERVER_IP = "127.0.0.1";
    public static final int PORT = 7000;
    //创建线程池
    private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler nettyClientHandler;
    private int count = 0;

    //编写方法使用代理模式，获取一个代理对象

    public Object getBean(final Class<?> serviceClass, String providerName) {
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[]{serviceClass}, ((proxy, method, args) -> {
            System.out.println("(proxy, method, args) 进入...." + (++count) + " 次");
            //{} 部分的代码，客户端每调用一次 hello, 就会进入到该代码
            if (nettyClientHandler == null) {
                initClient();
            }
            //设置要发给服务器端的信息
            //providerName 协议头 args[0] 就是客户端调用 api hello(???), 参数
            nettyClientHandler.setParam(providerName + args[0]);

            return pool.submit(nettyClientHandler).get();
        }));


        return o;
    }

    //初始化客户端
    private void initClient() throws InterruptedException {
        nettyClientHandler = new NettyClientHandler();
        //创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 逻辑代码块
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringEncoder())
                                    .addLast(new StringDecoder())
                                    .addLast(nettyClientHandler);
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(SERVER_IP, PORT).sync();

//            channelFuture.channel().closeFuture().sync();
        } finally {
            // 清理代码块
//            group.shutdownGracefully();  //加上这个group 直接就关闭了。不走 wait 之前 call 被调用 后续的逻辑
        }


    }
}