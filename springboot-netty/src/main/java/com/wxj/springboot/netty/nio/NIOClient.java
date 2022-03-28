package com.wxj.springboot.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/28 0028 14:32
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {

        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);

        //提供服务器端的ip 和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作..");
            }
        }
        //...如果连接成功，就发送数据
        String str = "hello,尚硅谷~";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //将要发送的buffer发送channel
        socketChannel.write(buffer);
        System.in.read();



    }
}