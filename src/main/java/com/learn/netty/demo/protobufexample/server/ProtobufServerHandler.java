package com.learn.netty.demo.protobufexample.server;

import com.learn.netty.demo.protobufexample.AddressBookProtos;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufServerHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.AddressBook msg) throws Exception {
        System.out.println(msg.getPeople(0));
    }
}
