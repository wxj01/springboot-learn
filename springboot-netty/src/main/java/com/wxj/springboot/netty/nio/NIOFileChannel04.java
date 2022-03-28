package com.wxj.springboot.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO -拷贝文件 transferFrom 方法
 * 1) 实例要求:
 * 2) 使用 FileChannel(通道) 和 方法 transferFrom ，完成文件的拷贝
 * 3) 拷贝一张图片
 * @date 2022/3/28 0028 13:24
 */
public class NIOFileChannel04 {
    public static void main(String[] args) {

        //创建相关流
        try(
                FileInputStream fileInputStream = new FileInputStream("d:\\1.png");
                FileOutputStream fileOutputStream = new FileOutputStream("d:\\2.png");
                //获取各个流对应的 filechannel
                final FileChannel sourceCh = fileInputStream.getChannel();
                final FileChannel destCh = fileOutputStream.getChannel();
        ) {
            //使用 transferForm 完成拷贝
            destCh.transferFrom(sourceCh,0,sourceCh.size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}