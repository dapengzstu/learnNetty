package com.learn.netty.deepunderstand._01simpleNetty.mynetty;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(9999);
        new Thread(reactor).start();
    }
}
