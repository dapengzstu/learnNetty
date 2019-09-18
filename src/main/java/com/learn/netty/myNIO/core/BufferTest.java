package com.learn.netty.myNIO.core;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.SecureRandom;
import java.util.Arrays;

public class BufferTest {
    public static void readFromNum(){
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0 ;i < buffer.capacity() - 5; i++){
            int randomNum = new SecureRandom().nextInt(20);
            //数据放入到Buffer中
            buffer.put(randomNum);
        }
        System.out.println("limit " + buffer);
        //Buffer的模式翻转
        buffer.flip();
        System.out.println("limit " + buffer);

        //数据从Buffer中拿出
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
    public static void readFromFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("hello.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //ByteBuffer.allocateDirect();
        //从文件中读到缓存中
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while(byteBuffer.remaining() > 0){
            byte b = byteBuffer.get();
            System.out.print((char) b);
        }

        fileInputStream.close();
    }
    public static void readFromByteArray() throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("hellocopy.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] message = "Hello byteArray".getBytes();
        for(int i = 0; i < message.length ; i++){
            byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();

    }
    public static void fileNioTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("hellocopy.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8);
        while (true){
            buffer.clear();
            //如果注释掉上面那一句，position和limit是同一个值，不可能在buffer中放入东西了，当再次write的时候，position回到0，又去重复写入
            int length = inputChannel.read(buffer);
            System.out.println(length + " length ");
            if (length == -1){
                break;
            }
            buffer.flip();
            int write = outputChannel.write(buffer);
            System.out.println(write + " write");
        }


        inputChannel.close();
        outputChannel.close();

    }
    public static void sliceTest(){
        IntBuffer intBuffer = IntBuffer.allocate(1024);
        for (int i = 0 ;i < 100 ; i ++){
            intBuffer.put(i);
        }

        intBuffer.position(2);
        intBuffer.limit(8);
        IntBuffer slice = intBuffer.slice();
        for (int j = 0 ; j < slice.capacity(); j++){
            System.out.println(slice.get());
        }

    }
    public static void scatterAndGatherTest() throws IOException {
        //scatter，将一个channel的东西读取到好几个Buffer中，Gather相对
        //场景，网络传递时候，通过不同的buffer将包里面的头和body分开
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        InetSocketAddress address = new InetSocketAddress(8898);
        serverSocketChannel.socket().bind(address);
        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[0] = ByteBuffer.allocate(3);
        byteBuffers[0] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        System.out.println("accept");
        while (true){
            int bytesRead = 0;
            while (bytesRead <  messageLength){
                long read = socketChannel.read(byteBuffers);
                bytesRead += read;
                System.out.println("byteRead : " + byteBuffers);
                Arrays.asList(byteBuffers).stream().map(b -> "position: " + b.position() + ",limit: " + b.limit()).forEach(System.out::println);
            }

            //写
            Arrays.asList(byteBuffers).forEach(buffer -> {
                buffer.flip();
            });

            long byteWritten = 0 ;
            while(byteWritten < messageLength){
                long write = socketChannel.write(byteBuffers);
                System.out.println("write :" + write);
                byteWritten += write;
            }

            Arrays.asList(byteBuffers).forEach(buffer ->{
                buffer.clear();
            });

            System.out.println("bytesRead: " + bytesRead + ", bytesWrite: " + byteWritten  +",messageLength: " + messageLength);
        }





    }
    public static void main(String[] args) throws IOException {
        //readFromNum();
        //readFromFile
        //readFromByteArray();
         //fileNioTest();
        //sliceTest();
        scatterAndGatherTest();
    }
}
