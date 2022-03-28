package com.wxj.springboot.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 4) 前面我们讲的读写操作，都是通过一个 Buffer 完成的，NIO 还支持 通过多个 Buffer (即 Buffer 数组) 完成读
 * 写操作，即 Scattering 和 Gathering
 *
 *  * Scattering：将数据写入到 buffer 时，可以采用 buffer 数组，依次写入 [分散]
 *  * Gathering: 从 buffer 读取数据时，可以采用 buffer 数组，依次读
 * @date 2022/3/28 0028 13:45
 */

public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        //使用 ServerSocketChannel 和 SocketChannel 网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到 socket ，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等客户端连接(telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();
        //假定从客户端接收 8 个字节
        int messageLength = 8;
        //循环的读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += 1L;//累计读取的字节数

                System.out.println("byteRead=" + byteRead);

                //使用流打印, 看看当前的这个 buffer 的 position 和 limit
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "postion=" + byteBuffer.position() + ", limit=" +
                        byteBuffer.limit())
                        .forEach(System.out::println);
            }
            //将所有的 buffer 进行 flip
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            //将数据读出显示到客户端
            long byteWrite = 0L;
            while (byteWrite < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += 1L;
            }
            //将所有的 buffer 进行 clear
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead:=" + byteRead + " byteWrite=" + byteWrite + ", messagelength" +
                    messageLength);
        }
    }
}