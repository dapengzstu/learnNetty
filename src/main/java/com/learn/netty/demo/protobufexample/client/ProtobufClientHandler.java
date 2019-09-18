package com.learn.netty.demo.protobufexample.client;

import com.learn.netty.demo.protobufexample.AddressBookProtos;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.AddressBook msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //构造出一个对象
        AddressBookProtos.Person person1 = AddressBookProtos.Person.newBuilder()
                .setEmail("dapengzstu@163.com")
                .setName("dapeng")
                .setId(20185344)
                .addPhones(
                        AddressBookProtos.Person.PhoneNumber.newBuilder()
                                .setNumber("1111111111")
                                .setType(AddressBookProtos.Person.PhoneType.HOME)
                ).build();


        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder().addPeople(person1).build();

        //发送给服务器
        ctx.channel().writeAndFlush(addressBook);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
