package com.learn.netty.demo._3websocketexample.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        channelPipeline.addLast("ChunkedWriteHandler",new ChunkedWriteHandler());
        channelPipeline.addLast("HttpObjectAggregator",new HttpObjectAggregator(8192));
        channelPipeline.addLast("",new WebSocketServerProtocolHandler("/ws"));
        channelPipeline.addLast("WebSocketServerHandler",new WebSocketServerHandler());
    }
}
