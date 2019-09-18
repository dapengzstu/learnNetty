package com.learn.netty.demo._2socketexample.service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MySocketServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        //解码器
        channelPipeline.addLast("LengthFieldBasedFrameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
        //编码器
        channelPipeline.addLast("LengthFieldPrepender",new LengthFieldPrepender(4));

        channelPipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast("StringEncoder",new StringEncoder((CharsetUtil.UTF_8)));

        //自己的处理器
        channelPipeline.addLast("MySocketServerHandler",new MySocketServerHandler());
    }
}
