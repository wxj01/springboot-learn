package com.wxj.springboot.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO NIOFileChannel 学习
 * 1) 使用前面学习后的 ByteBuffer(缓冲) 和 FileChannel(通道)， 将 file01.txt 中的数据读入到程序，并显示在控制
 * 台屏幕
 * 2) 假定文件已经存在
 * @date 2022/3/28 0028 13:08
 */
public class NIOFileChannel02 {
    public static void main(String[] args) {

        //创建文件的输入流
        File file = new File("d:\\file01.txt");
        try ( FileInputStream fileInputStream = new FileInputStream(file)){

            //通过 fileInputStream 获取对应的 FileChannel -> 实际类型 FileChannelImpl
            FileChannel fileChannel = fileInputStream.getChannel();
            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            //将 通道的数据读入到 Buffer
            fileChannel.read(byteBuffer);
            //将 byteBuffer 的 字节数据 转成 String
            System.out.println(new String(byteBuffer.array()));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}