package com.wxj.springboot.netty.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO  NIOFileChannel 学习
 * 1) 使用前面学习后的 ByteBuffer(缓冲) 和 FileChannel(通道)， 将 "hello,尚硅谷" 写入到 file01.txt 中
 * 2) 文件不存在就创建
 * @date 2022/3/28 0028 13:03
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello,尚硅谷";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        //通过 fileOutputStream 获取 对应的 FileChannel
        //这个 fileChannel 真实 类型是 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        //将 str 放入 byteBuffer
        byteBuffer.put(str.getBytes());

        //对 byteBuffer 进行 flip
        byteBuffer.flip();
        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}