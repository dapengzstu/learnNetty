package com.learn.netty.demo.protobufexample.server;

import com.learn.netty.demo.protobufexample.AddressBookProtos;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtobufServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //四个处理器Netty提供针对ProtoBuf
        channelPipeline.addLast(new ProtobufVarint32FrameDecoder());
        //解码器,写入要解码的那个接口,写死了，很不好
        channelPipeline.addLast(new ProtobufDecoder(AddressBookProtos.AddressBook.getDefaultInstance()));
        channelPipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        //编码的
        channelPipeline.addLast(new ProtobufEncoder());
        channelPipeline.addLast(new ProtobufServerHandler());
    }
}
