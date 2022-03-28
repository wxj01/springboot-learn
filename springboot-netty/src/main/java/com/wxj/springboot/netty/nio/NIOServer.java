package com.wxj.springboot.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * 1) 编写一个 NIO 入门案例，实现服务器端和客户端之间的数据简单通讯（非阻塞）
 * 2) 目的：理解 NIO 非阻塞网络编程机制
 * @date 2022/3/28 0028 14:11
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {

        //创建ServerSocketChannel -》 ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个Selector 对象
        Selector selector = Selector.open();

        //绑定一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //设置非阻塞
        serverSocketChannel.configureBlocking(false);

        //把 serverSocketChannel 注册到 selector 关心 事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true) {
            //这里我们等待 1 秒，如果没有事件发生, 返回
            if (selector.select(1000) == 0) {
                //没有事件发生
                System.out.println("服务器等待了 1 秒，无连接");
                continue;
            }

            //如果返回的>0, 就获取到相关的 selectionKey 集合
            //1.如果返回的>0， 表示已经获取到关注的事件
            //2. selector.selectedKeys() 返回关注事件的集合
            // 通过 selectionKeys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历 Set<SelectionKey>, 使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                //获取到 SelectionKey
                //根据 key 对应的通道发生的事件做相应处理
                if (key.isAcceptable()) {//如果是 OP_ACCEPT, 有新的客户端连接
                    //该该客户端生成一个 SocketChannel
                    try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                        System.out.println(" 客 户 端 连 接 成 功 生 成 了 一 个 socketChannel " +
                                socketChannel.hashCode());

                        //将 SocketChannel 设置为非阻塞
                        socketChannel.configureBlocking(false);

                        //将 socketChannel 注册到 selector, 关注事件为 OP_READ， 同时给 socketChannel
                        //关联一个 Buffer
                        socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if (key.isReadable()) {//发生 OP_READ
                    //通过 key 反向获取到对应 channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该 channel 关联的 buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    try {
                        channel.read(buffer);
                        System.out.println("form 客户端 " + new String(buffer.array()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //手动从集合中移动当前的 selectionKey, 防止重复操作
                keyIterator.remove();
            }

            //forEach 删除元素有问题
//            selectionKeys.forEach(key -> {
//
//            });
        }
    }
}