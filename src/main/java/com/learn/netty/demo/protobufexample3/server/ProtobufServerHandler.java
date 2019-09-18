package com.learn.netty.demo.protobufexample3.server;

import com.learn.netty.demo.protobufexample2.MyMessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufServerHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) throws Exception {
        MyMessageInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType.equals(MyMessageInfo.MyMessage.DataType.PersonType)){
            System.out.println(msg.getPerson());
        }else if(dataType.equals(MyMessageInfo.MyMessage.DataType.DogType)){
            System.out.println(msg.getDog());
        }else {
            System.out.println(msg.getCat());
        }
    }
}
