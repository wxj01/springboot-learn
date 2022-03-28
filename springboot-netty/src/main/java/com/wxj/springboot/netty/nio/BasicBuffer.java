package com.wxj.springboot.netty.nio;

import java.nio.IntBuffer;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO nio 学习
 * 案例说明 NIO 的 Buffer
 * @date 2022/3/28 0028 12:59
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //举例说明 Buffer 的使用 (简单说明)
        //创建一个 Buffer, 大小为 5, 即可以存放 5 个 int

        IntBuffer allocate = IntBuffer.allocate(5);
        for (int i = 0; i < allocate.capacity(); i++) {
            allocate.put(i* 2);
        }

        //如何从 buffer 读取数据
        //将 buffer 转换，读写切换(!!!)
        allocate.flip();

        while (allocate.hasRemaining()){
            System.out.println(allocate.get());
        }
    }
}