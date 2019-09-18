package com.learn.netty.demo._1firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 *  这是一个http的程序
 * */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {

        //两个死循环，不断接受客户端发起的链接，链接进来就处理，然后继续循环
        //boss接受链接，转给work
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //用于启动服务端的类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
                //boss接受，然后转给work
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    //子处理器，请求到来的时候用这个请求处理器来处理
                    .childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }




    }
}
