package com.learn.netty.demo.protobufexample3.common;

import com.google.protobuf.MessageLite;
import com.learn.netty.demo.protobufexample3.DogMessage;
import com.learn.netty.demo.protobufexample3.PersonMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyProtobufDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //获取包头中body长度
        byte low = in.readByte();
        byte high = in.readByte();
        short s0 = (short)(low & 0xff);
        short s1 = (short)(high & 0xff);
        s1 <<= 8;
        short length = (short)(s0 | s1);

        //获取msg的类类型
        in.readByte();
        byte type = in.readByte();
        //判断可读的byte长度与length
        if(in.readableBytes() != length){
            in.resetReaderIndex();
            return;
        }
        //读取
        ByteBuf body = in.readBytes(length);
        byte[] array = null;
        int readableLen = in.readableBytes();
        if(body.hasArray()){

        }else {
            array = new byte[length];
            body.getBytes(body.readerIndex(),array,0,length);
        }

        //反序列化
        MessageLite result = null;
        if(type == 0x00){
             result = PersonMessage.Person.getDefaultInstance().getParserForType().parseFrom(array,0,readableLen);
        }else if(type == 0x01){
            result = DogMessage.Dog.getDefaultInstance().getParserForType().parseFrom(array,0,readableLen);
        }
        out.add(result);
    }
}
