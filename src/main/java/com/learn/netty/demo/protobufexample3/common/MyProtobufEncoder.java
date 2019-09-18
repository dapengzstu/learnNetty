package com.learn.netty.demo.protobufexample3.common;

import com.google.protobuf.MessageLite;
import com.learn.netty.demo.protobufexample3.DogMessage;
import com.learn.netty.demo.protobufexample3.PersonMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyProtobufEncoder extends MessageToByteEncoder<MessageLite> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {
        byte[] msgBytes = msg.toByteArray();
        byte[] header = encoderHeader(msg,(short)msgBytes.length);
        out.writeBytes(header);
        out.writeBytes(msgBytes);
        return ;
    }

    private byte[] encoderHeader(MessageLite msg,short msgBytes){
        byte messageType = 0x0f;
        if(msg instanceof DogMessage.Dog){
            messageType = 0x00;
        }else if(msg instanceof PersonMessage.Person){
            messageType = 0x01;
        }
        byte[] header = new byte[4];
        header[0] = (byte)(msgBytes & 0xff);
        header[1] = (byte)((msgBytes >> 8) & 0xff);
        header[2] = 0;
        header[3] = messageType;

        return header;
    }
}
