package com.learn.netty.myNIO.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOClient {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost",8899));
            while (true){
                //一旦服务器端返回东西就会由阻塞状态改为运行
                int select = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    try{
                        if (selectionKey.isConnectable()){
                            SocketChannel client = (SocketChannel)selectionKey.channel();
                            if (client.isConnectionPending()){
                                //判断是否是将要链接建立的状态
                                try {
                                    client.finishConnect();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    byteBuffer.put((LocalDateTime.now() + "链接成功").getBytes());
                                    byteBuffer.flip();
                                    client.write(byteBuffer);

                                    executorService.submit(() ->{
                                        while (true){
                                            try {
                                                byteBuffer.clear();
                                                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                                BufferedReader br = new BufferedReader(inputStreamReader);
                                                String sendMessage = br.readLine();
                                                byteBuffer.put(sendMessage.getBytes());
                                                byteBuffer.flip();
                                                client.write(byteBuffer);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            //将这个链接注册

                            client.register(selector,SelectionKey.OP_READ);

                        }else if (selectionKey.isReadable()){
                            SocketChannel client = (SocketChannel) selectionKey.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int read1 = client.read(readBuffer);
                            if (read1 > 0){
                                String receivedMessage =  new String(readBuffer.array(),0,read1);
                                System.out.println(receivedMessage);
                            }

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                });
                //把那个注册监听connect的通道给弄了
                selectionKeys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
