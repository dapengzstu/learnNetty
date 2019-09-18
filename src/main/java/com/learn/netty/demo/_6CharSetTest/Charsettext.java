package com.learn.netty.demo._6CharSetTest;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class Charsettext {
    public static void main(String[] args) throws IOException {
        String inputFile = "NIOCharsetTest_In.txt";
        String outputFile = "NIOCharsetTest_out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile,"rw");

        long length = new File(inputFile).length();
        FileChannel inputChannel = inputRandomAccessFile.getChannel();
        FileChannel outputChannel = outputRandomAccessFile.getChannel();

        //内容映射一下
        MappedByteBuffer map = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        //解码
        Charset charset = Charset.forName("utf-8");
        //解码器
        CharsetDecoder charsetDecoder = charset.newDecoder();
        //编码器
        CharsetEncoder charsetEncoder = charset.newEncoder();
        //解码操作
        CharBuffer decode = charsetDecoder.decode(map);
        //编码操作
        ByteBuffer encode = charsetEncoder.encode(decode);

        int write = outputChannel.write(encode);
        inputChannel.close();
        outputChannel.close();


    }
}
