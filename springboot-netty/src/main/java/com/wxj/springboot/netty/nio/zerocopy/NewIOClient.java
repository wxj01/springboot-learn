package com.wxj.springboot.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/28 0028 15:55
 */
public class NewIOClient {
    public static void main(String[] args) {


        long startTime;
        long transferCount;
        try (SocketChannel socketChannel = SocketChannel.open()) {

            socketChannel.connect(new InetSocketAddress("localhost", 7001));
            String filename = "springboot-netty/src/main/resources/protoc-3.6.1-win32.zip";

            //得到一个文件 channel
            FileInputStream fileInputStream = new FileInputStream(filename);
            FileChannel fileChannel = fileInputStream.getChannel();

            //准备发送
            startTime = System.currentTimeMillis();
            //在 linux 下一个 transferTo 方法就可以完成传输
            //在 windows 下 一次调用 transferTo 只能发送 8m , 就需要分段传输文件, 而且要主要
            //传输时的位置 =》 课后思考... //transferTo 底层使用到零拷贝

            transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            System.out.println(" 发 送 的 总 的 字 节 数 =" + transferCount + " 耗 时 :" + (System.currentTimeMillis() - startTime));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}