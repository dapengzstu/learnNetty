package com.learn.netty.demo._5chatexample.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //这个是向服务器发出消息的channel
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if(ch != channel){
                ch.writeAndFlush(channel.remoteAddress() + ":::::" + msg + "\n");
            }else{
                ch.writeAndFlush(("Myself " + msg + "\n"));
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //表示链接处于活动状态
        Channel channel = ctx.channel();
        System.out.println( channel.remoteAddress() + "on line" + "\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "out line"+ "\n");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        /**
         * handler和服务器已经建立链接侯，每一个链接都有一个channel，如果要转发消息，就要把所有的channel都要收集起来，然后转发
         */

        Channel channel = ctx.channel();
        //告诉别的客户端有一个channel加入了
        channelGroup.writeAndFlush("service -> " + channel.remoteAddress() + "come here ." + "\n");
        System.out.println(channelGroup.size());
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //断掉的时候Netty会自动移除这个链接
        //channelGroup.remove(channel);

        channelGroup.writeAndFlush("service -> " + channel.remoteAddress() + "gone." + "\n" );
        System.out.println(channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
