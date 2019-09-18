package com.learn.netty.demo._8ZeroCopy.neew;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel =  SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress("localhost",9988));
        String fileName = "/usr/code.deb";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileChannel channel = fileInputStream.getChannel();
        
        long currentTime = System.currentTimeMillis();
        //一行代码就传出去了
        final long l = channel.transferTo(0, channel.size(), socketChannel);

        System.out.println("总共传输了字节:" + channel.size() + "，用时" + (System.currentTimeMillis() - currentTime));
        channel.close();
    }
}
