package com.learn.netty.demo._8ZeroCopy.neew;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(9988);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        //设置
        socket.setReuseAddress(true);
        socket.bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            int read = 0;
            while (read != -1){
                try{
                    read = socketChannel.read(byteBuffer);
                }catch (Exception e){
                    e.printStackTrace();
                }

                byteBuffer.rewind();
            }
        }
    }
}
