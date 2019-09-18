package com.learn.netty.demo._8ZeroCopy.old;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9988);
        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            try{
                byte[] bytes= new byte[4096];
                while (true){
                    //每次读取4096个
                    int read = dataInputStream.read(bytes,0,bytes.length);

                    if( -1 == read){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {

            }
        }
    }
}
