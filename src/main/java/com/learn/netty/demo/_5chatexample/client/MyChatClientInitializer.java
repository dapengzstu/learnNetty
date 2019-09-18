package com.learn.netty.demo._5chatexample.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyChatClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        //跟据分隔符对消息解码
        channelPipeline.addLast("DelimiterBasedFrameDecoder",new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));

        channelPipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast("StringEncoder",new StringEncoder((CharsetUtil.UTF_8)));

        //自己的处理器
        channelPipeline.addLast("MySocketServerHandler",new MyChatClientHandler());
    }
}
