package com.wxj.springboot.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 1) ByteBuffer 支持类型化的 put 和 get, put 放入的是什么数据类型，get 就应该使用相应的数据类型来取出，否
 * 则可能有 BufferUnderflowException 异常
 * @date 2022/3/28 0028 13:29
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        //创建一个 Buffer
        final ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        byteBuffer.putInt(100);
        byteBuffer.putLong(9L);
        byteBuffer.putChar('尚');
        byteBuffer.putShort((short) 4);

        //取出
        byteBuffer.flip();
        System.out.println();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
//        System.out.println(byteBuffer.getInt()); //java.nio.BufferUnderflowException
    }
}