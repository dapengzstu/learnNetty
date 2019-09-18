package com.learn.netty.demo._1firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        //HttpServerCodec 是对进来的Httprequest和response做出解码的动作
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //具体请求操作
        pipeline.addLast("TestHttpServerHandler",new TesthttpServerHandler());

    }
}
