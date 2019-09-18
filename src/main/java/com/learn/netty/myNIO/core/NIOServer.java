package com.learn.netty.myNIO.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.logging.Logger;

/**
*       一个聊天的程序
 *       ServerSocketChannel    ::  只关注连接的事件
 *       SocketChannel          ::  关注读取的事件
* */
public class NIOServer {
    private static Logger logger = Logger.getLogger(NIOServer.class.getName());

    private static Map<String,SocketChannel> map = new HashMap<String,SocketChannel>();
    public static void processThread(SocketChannel sc){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteBuffer byteBuffer  = ByteBuffer.allocate(1024);
                int read = 0;
                //读取客户端的内容
                try {
                    read = sc.read(byteBuffer);

                    //转发
                    if (read > 0){
                        //转发
                        byteBuffer.flip();
                        byteBuffer.mark();
                        Set<String> strings = map.keySet();
                        strings.forEach(str ->{
                            SocketChannel socketChannel = map.get(str);
                            try {
                                Thread.sleep(2000);
                                socketChannel.write(byteBuffer);
                                //每次用完bytebuffer的时候都要将position放在0号位置
                                byteBuffer.reset();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //获取到服务器端的socket
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));


        Selector selector = Selector.open();
        //注册
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //事件处理
        while (true){
            try {
                //阻塞等到关注的事件发生
                int select = selector.select();
                //通过事件，还可以获取对应的channel,Set表示可能一下子来了多个链接
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //对于返回每个selectionkey做出处理
                selectionKeys.forEach(selectionkey ->{
                    SocketChannel client;
                    try {
                        if (selectionkey.isAcceptable()){
                            ServerSocketChannel ssc= (ServerSocketChannel) selectionkey.channel();
                            //返回一个SocketChannel，
                            client = ssc.accept();
                            client.configureBlocking(false);
                            //下一次，selector就会监听这个客户端的OP_READ事件，也就是对于服务端来说有可读的东西来了
                            client.register(selector,SelectionKey.OP_READ);
                            String key = "[" + UUID.randomUUID() + "]";
                            System.out.println(key + "连接");
                            map.put(key,client);
                        }else if (selectionkey.isReadable()){
                            SocketChannel sc = (SocketChannel) selectionkey.channel();
                            //新线程处理
                            processThread(sc);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                });
                selectionKeys.clear();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
