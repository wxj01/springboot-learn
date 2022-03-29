package com.wxj.springboot.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 举例说明 Unpooled 获取 Netty 的数据容器 ByteBuf 的基本使用
 * @date 2022/3/29 0029 10:58
 */
public class NettyByteBuf01 {

    public static void main(String[] args) {
        //创建一个 ByteBuf
        //说明
        //1. 创建 对象，该对象包含一个数组 arr , 是一个 byte[10]
        //2. 在 netty 的 buffer 中，不需要使用 flip 进行反转
        // 底层维护了 readerindex 和 writerIndex
        //3. 通过 readerindex 和 writerIndex 和 capacity， 将 buffer 分成三个区域
        // 0---readerindex 已经读取的区域
        // readerindex---writerIndex ， 可读的区域
        // writerIndex -- capacity, 可写的区域

        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.writeByte(i);
        }

        System.out.println("capacity=" + buffer.capacity());//10
        //输出
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
        System.out.println("执行完毕");
    }
}