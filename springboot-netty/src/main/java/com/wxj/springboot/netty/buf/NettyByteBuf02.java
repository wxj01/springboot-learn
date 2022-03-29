package com.wxj.springboot.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/3/29 0029 11:02
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
        //创建 ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world!", CharsetUtil.UTF_8);
        //使用相关的方法
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();
            //将 content 转成字符串
            String s = new String(content, Charset.forName("utf-8"));
            System.out.println(s);

            System.out.println("byteBuf = "+byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.getByte(0));

            int len = byteBuf.readableBytes();
            System.out.println("len=" + len);

            for (int i = 0; i < len; i++) {
                System.out.println(byteBuf.getByte(i));
            }

            //按照某个范围读取
            System.out.println(byteBuf.getCharSequence(0,4,Charset.forName("utf-8")));
            System.out.println(byteBuf.getCharSequence(4,6,Charset.forName("utf-8")));

        }
    }
}