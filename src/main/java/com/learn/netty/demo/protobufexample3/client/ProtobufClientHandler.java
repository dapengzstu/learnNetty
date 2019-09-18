package com.learn.netty.demo.protobufexample3.client;

import com.learn.netty.demo.protobufexample2.MyMessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int i = new Random().nextInt(3);
        MyMessageInfo.MyMessage message = null;

        //构造出一个对象
        if( 0 == i){
            //传入Person
            MyMessageInfo.Person person = MyMessageInfo.Person.newBuilder().setAddress("jida").setName("zhuopeng").setAge(22).build();

            message = MyMessageInfo.MyMessage.newBuilder().setDataType(MyMessageInfo.MyMessage.DataType.PersonType)
                    .setPerson(person).build();

        }else if( 1 == i){
            //传入Dog
            MyMessageInfo.Dog dog = MyMessageInfo.Dog.newBuilder().setAge(2).setName("bobo").build();
            message = MyMessageInfo.MyMessage.newBuilder().setDataType(MyMessageInfo.MyMessage.DataType.DogType)
                    .setDog(dog).build();
        }else{
            MyMessageInfo.Cat cat = MyMessageInfo.Cat.newBuilder().setCity("changchun").setName("miaomiao").build();
            message = MyMessageInfo.MyMessage.newBuilder().setDataType(MyMessageInfo.MyMessage.DataType.CatType)
                    .setCat(cat).build();
        }

        //发送给服务器
        ctx.channel().writeAndFlush(message);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
