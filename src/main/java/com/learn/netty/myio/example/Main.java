package com.learn.netty.myio.example;

import java.io.*;

public class Main {
    public static void fileStreamTest() throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        //一个文件节点流
        try {
            fileInputStream = new FileInputStream("test.txt");
            fileOutputStream = new FileOutputStream("testout.txt");
            int read;
            System.out.println("has found the test.txt");
            while((read = fileInputStream.read() )!= -1){
                fileOutputStream.write(read);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            fileInputStream.close();
            fileOutputStream.close();
        }


    }
    public static void byteArrayStreamTest(){
        byte []bytes = new byte[] {1,2,3,4,5,5,3,3,3,3,'q','w','e'};
        byte []byteOut = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            int read;
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            byteArrayOutputStream = new ByteArrayOutputStream();
            while( (read = byteArrayInputStream.read()) != -1){
                byteArrayOutputStream.write(read);
            }
            byteOut = byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (byte b:
             byteOut) {
            System.out.println(b);
        }

    }
    /**
     *  管道如果读的一方关闭，但是写的一方还在继续，就会出错。
     *  如果写的一方关闭，读的一方持续读，也会出错
     *  可以设置一个结束信号，或者双方读写次数一样。
     * */
    public static void pipedStreamTest() throws IOException {
        final PipedInputStream pIn = new PipedInputStream();
        final PipedOutputStream pOut = new PipedOutputStream();
        //进出相连
        pIn.connect(pOut);
        //往管道里面写内容
        Thread outThread = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {

                    pOut.write(i);
                    pOut.flush();
                    Thread.sleep(300);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                pOut.write((int)'h');
                pOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        //从管道里面读出内容
        Thread inThread = new Thread(() -> {
            int read;
            while (true) {
                try {
                    read = pIn.read();
                    if (read == 'h'){
                        break;
                    }
                    System.out.println(read);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        outThread.start();
        inThread.start();


        try {
            inThread.join();
            outThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pIn.close();
        pOut.close();
    }
    public static void sequenceSttreamTest() throws IOException {
        InputStream inputStream1 = new FileInputStream("test.txt");
        byte []bytes = new byte[] {1,2,3,4,5,5,3,3,3,3};
        InputStream inputStream2 = new ByteArrayInputStream(bytes);

        InputStream sequenceInputStream = new SequenceInputStream(inputStream1,inputStream2);
        int read;
        while((read = sequenceInputStream.read()) != -1){
            System.out.println((char)read);
        }
        sequenceInputStream.close();
    }
    public static void dataStreamTest() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("hello.txt"));
        dataOutputStream.writeUTF("32.2Hello11");
        dataOutputStream.flush();
        dataOutputStream.close();

        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("hello.txt"));
        String s = dataInputStream.readUTF();
        System.out.println(s);
        dataInputStream.close();

    }
    public static void pushbackStreamTest() throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(new FileInputStream("hello.txt"));
        int read = pushbackInputStream.read();
        System.out.println((char)read);
        pushbackInputStream.unread(read);
        pushbackInputStream.close();
    }
    public static void bufferedStreamTest() throws IOException {
        InputStream inputStream  = new FileInputStream("hello.txt");
        OutputStream outputStream = new FileOutputStream("hellocopy.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] b = new byte[1024];
        int length;
        while ((length = bufferedInputStream.read(b)) != -1){
            System.out.println(length);
            //先放在内存里面
            bufferedOutputStream.write(b,0,length);

        }
        //一次刷新
        bufferedOutputStream.flush();

        bufferedInputStream.close();
        bufferedOutputStream.close();

    }
    public static void main(String[] args) throws IOException {
        //节点流例子
        //fileStreamTest();
        //byteArrayStreamTest();
        //pipedStreamTest();
        //sequenceSttreamTest();

        //过滤流例子
        //dataStreamTest();
        //pushbackStreamTest();
        bufferedStreamTest();
    }
}
