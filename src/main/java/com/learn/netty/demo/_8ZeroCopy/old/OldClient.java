package com.learn.netty.demo._8ZeroCopy.old;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class OldClient {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost",9988);
        String fileName = "/usr/code.deb";
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        long startTime = System.currentTimeMillis();
        int total = 0;
        int readCount;
        while((readCount = inputStream.read(bytes)) >= 0){
            total += readCount;
            dataOutputStream.write(bytes);
        }
        System.out.println("发送字节：" + total + "个，耗时"+ (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
