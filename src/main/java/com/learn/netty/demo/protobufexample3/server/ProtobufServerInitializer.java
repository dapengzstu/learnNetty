package com.learn.netty.demo.protobufexample3.server;

import com.learn.netty.demo.protobufexample3.common.MyProtobufDecoder;
import com.learn.netty.demo.protobufexample3.common.MyProtobufEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ProtobufServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new MyProtobufDecoder());
        channelPipeline.addLast(new MyProtobufEncoder());

        channelPipeline.addLast(new ProtobufServerHandler());
    }
}
