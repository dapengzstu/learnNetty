package com.learn.netty.demo.grpc.server;


import com.learn.netty.demo.grpc.idl.service.StudentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class GrpcServer01 {
    private static final Logger logger = Logger.getLogger(GrpcServer01.class.getName());
    private Server server = null;

    public void start() throws IOException {
        int port = 50051;
        //启动
        this.server = ServerBuilder.forPort(port)
                .addService(new StudentServiceImpl())
                .build()
                .start();
        logger.info("Server started , listening on " + port);

        //一个钩子函数,在一个应用启动以后就会jvm创建一个Runtime单例对象，只能被getRuntime()创建
        //addShutdownHook jvm结束的时候就会调用这个线程
        Runtime.getRuntime().addShutdownHook(
                new Thread(()->{
                    System.out.println("shutdown jvm");
                    GrpcServer01.this.stop();
                })
        );

        logger.info("=======================");

    }
    private void stop(){
        if(null != this.server){
            this.server.shutdown();
        }
    }
    private void awaitTermination() throws InterruptedException {

        if(null != this.server){
            //this.server.awaitTermination(3000,TimeUnit.MILLISECONDS);
            this.server.awaitTermination();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer01 grpcServer01 = new GrpcServer01();
        grpcServer01.start();
        //等待传输
        grpcServer01.awaitTermination();
    }
}
