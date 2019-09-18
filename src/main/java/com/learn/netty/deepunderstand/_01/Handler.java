package com.learn.netty.deepunderstand._01;



import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {
    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final  int READING = 0,SENDING = 1;
    Selector selector;
    int state = READING;
    public  Handler(SocketChannel socketChannel , Selector selector) throws IOException {
        this.socketChannel = socketChannel;
        this.selector = selector;
        socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
        selectionKey.attach(this);

        //selectionKey.interestOps(SelectionKey.OP_READ);
        this.selector.wakeup();
    }

    @Override
    public void run() {
        try{
            if (state == READING)
                read();
            else if(state == SENDING)
                send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void read(){
        processRead();
        state = SENDING;
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }
    public void send(){
        processSend();
    }
    public void processSend(){
        output.flip();
        try {
            socketChannel.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            output.clear();
            state = READING;
            selectionKey.interestOps(SelectionKey.OP_READ);
        }
    }
    public void processRead(){
        try {
            int read = socketChannel.read(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
