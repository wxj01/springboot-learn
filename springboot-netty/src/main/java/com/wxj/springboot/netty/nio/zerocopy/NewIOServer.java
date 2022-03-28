package com.wxj.springboot.netty.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 1) 使用传统的 IO 方法传递一个大文件
 * 2) 使用 NIO 零拷贝方式传递(transferTo)一个大文件
 * 3) 看看两种传递方式耗时时间分别是多少
 * @date 2022/3/28 0028 15:49
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {

        InetSocketAddress address = new InetSocketAddress(7001);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(address);

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                }catch (Exception e){
                    break;
                }

                byteBuffer.rewind(); //倒带 position = 0 mark 作废
            }
        }
    }
}