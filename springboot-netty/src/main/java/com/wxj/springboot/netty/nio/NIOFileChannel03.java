package com.wxj.springboot.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO NIOFileChannel学习
 * 1) 使用 FileChannel(通道) 和 方法 read , write，完成文件的拷贝
 * 2) 拷贝一个文本文件 1.txt , 放在项目下即可
 * @date 2022/3/28 0028 13:13
 */
public class NIOFileChannel03 {
    public static void main(String[] args) {
        try(
                FileInputStream fileInputStream = new FileInputStream("springboot-netty/src/main/resources/1.txt");
                FileOutputStream fileOutputStream = new FileOutputStream("springboot-netty/src/main/resources/2.txt")
        ) {
             FileChannel fileChannel01 = fileInputStream.getChannel();
             FileChannel fileChannel02 = fileOutputStream.getChannel();

             ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            //循环读取
            while (true) {
                //这里有一个重要的操作，一定不要忘了
                byteBuffer.clear();//清空 buffer
                int read = fileChannel01.read(byteBuffer);
                System.out.println("read =" + read);
                if (read == -1) {//表示读完
                    break;
                }
                //将 buffer 中的数据写入到 fileChannel02 -- 2.txt
                byteBuffer.flip();
                fileChannel02.write(byteBuffer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}