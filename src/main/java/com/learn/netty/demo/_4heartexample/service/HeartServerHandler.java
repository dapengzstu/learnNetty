package com.learn.netty.demo._4heartexample.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //事件触发
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null;
            //分析是哪种空闲
            switch (event.state()){
                case ALL_IDLE:
                    eventType = "read and write idle";
                    break;
                case READER_IDLE:
                    eventType = "read idle";
                    break;
                case WRITER_IDLE:
                    eventType = "write idle";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + " overtime type " + eventType);

            ctx.channel().close();
        }
    }
}
