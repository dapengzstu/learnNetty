package com.learn.netty.deepunderstand._01simpleNetty.mynetty;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){

                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator iterator = selected.iterator();
                while (iterator.hasNext()){
                    dispatch((SelectionKey) iterator.next());
                    iterator.remove();
                }
                selected.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void dispatch(SelectionKey selectionKey){
        //如果是ServerSocketChannel启动自带的Acceptor的run方法，把一个新的到来的SocketChannel给注册到selector中
        //如果是SocketChannel，则启动Handler的run方法，判断读写，然后具体操作
        Runnable r = (Runnable) selectionKey.attachment();
        if (r != null){
            r.run();
        }
    }
    class Acceptor implements  Runnable {
        @Override
        public void run() {
            try{
                SocketChannel socketChannel = serverSocket.accept();
                if (socketChannel != null){
                    new Handler(socketChannel,selector);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}