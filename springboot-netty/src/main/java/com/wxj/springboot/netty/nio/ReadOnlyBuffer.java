package com.wxj.springboot.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 2) 可以将一个普通 Buffer 转成只读 Buffer
 * @date 2022/3/28 0028 13:32
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        //创建一个 buffer
        final ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //读取
        buffer.flip();

        //得到一个只读的 Buffer
        final ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        //读取
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte)100); //ReadOnlyBufferException
    }
}