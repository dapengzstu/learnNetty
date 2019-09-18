package com.learn.netty.myNIO.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    public static void main(String[] args) throws IOException {
        //服务器端监听5个端口号
        int[] ports = new int[5];
        for (int i = 0 ; i < 5 ; i++){
            ports[i] = 9000 + i;
        }
        /**
        / 1.    创建一个selector
        /
        /*/
        Selector selector = Selector.open();
        /**
         *
         * 2.    创建多个Channel，并注册在Selector上
         * */
        for(int i = 0 ;i < 5 ; i ++){
            //创建一个channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //配置Channel 为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //配置Channel 端口监听
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            //注册Channel 到Selector上。通道触发了一个事件意思是该事件已经就绪，准备好接入的连接称为“接收就绪”，selector就会关注这个通道连接过来的事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口 : " + ports[i]);
        }

        while (true){
            /**
             *      3.线程阻塞住，直到通道中关注的事件发生才返回。，所以是等待事件的发生，而不是轮询
             * */
            int select = selector.select();
            System.out.println("select: " + select);
            /**
             *
             *      4.获取注册好的通道，发出的准备好了的SelectionKey,表示一个或者多个关注的事件到来
             * */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys: " + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //如果是Accept就绪
                if (selectionKey.isAcceptable()){
                    //获取到对应的channel
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //设置通道为 读就绪，selector就会关注这个通道是否变为一个可读的
                    socketChannel.register(selector,SelectionKey.OP_READ);

                    System.out.println("获取到客户端链接： " + socketChannel);
                }else if (selectionKey.isReadable()){
                //如果是可读的
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int bytesRead = 0;
                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read < 0){
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        bytesRead += read;
                    }
                    System.out.println("读取： " + bytesRead + "，来自于" + socketChannel);
                }

                iterator.remove();

            }
        }

    }
}
