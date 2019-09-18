package com.learn.netty.demo.grpc.client;

import com.learn.netty.demo.grpc.idl.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.logging.Logger;
/**
 *
 *
 * */
public class GrpcClient01 {
    private final static Logger logger = Logger.getLogger(GrpcClient01.class.getName());
    private final ManagedChannel managedChannel ;
    //一种阻塞的调用方式
    private final StudentServiceGrpc.StudentServiceBlockingStub blockingStub ;
    //一种异步的调用，不会等待结果的返回
    private final StudentServiceGrpc.StudentServiceStub serviceStub ;
    //一种异步的调用，等待结果的返回
    //private final StudentServiceGrpc.StudentServiceFutureStub serviceFutureStub;

    public GrpcClient01(String host,int port){
        this(ManagedChannelBuilder.forAddress(host,port).usePlaintext().build());
    }
    public GrpcClient01(ManagedChannel channel){
        this.managedChannel = channel;
        blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        serviceStub = StudentServiceGrpc.newStub(channel);
        //serviceFutureStub = StudentServiceGrpc.newFutureStub(channel);
    }

    public void biTalk(){
        StreamObserver<StreamRequest> streamRequestStreamObserver = serviceStub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println("response " + value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });
        for(int i = 0 ; i < 10 ; i ++){
            streamRequestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo("hello").build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void request(String name) throws InterruptedException {
        logger.info("will try to request " + name + "...");

        MyRequest request = MyRequest.newBuilder().setUsername(name).build();
        MyResponse response ;

        //1.    放入一个数据，返回一个数据
        response = blockingStub.getRealNameByUsername(request);
        logger.info("response message " + response.getRealname());
            //第二次请求，会复用上一次的tcp连接
        response = blockingStub.getRealNameByUsername(request);
        logger.info("response message " + response.getRealname());

        //2.    放入一个数据，返回一个流
        StudentRequest studentRequest = StudentRequest.newBuilder().setAge(12).build();
        Iterator<StudentResponse> students = blockingStub.getStudentByAge(studentRequest);
        while (students.hasNext()){
            logger.info("studentResponse :  " + students.next().getName());
        }

        //3.    放入一个流，返回一个数据
        //只要客户端用流式的方式向服务器发送请求，这种请求就是异步的
        StreamObserver<StudentResponse> studentResponseStreamObserver = new StreamObserver<StudentResponse>() {
            //这是一个回调类
            @Override
            public void onNext(StudentResponse value) {
                //得到响应的结果
                System.out.println("*************");
                System.out.println(value.getName());
                System.out.println(value.getAge());
                System.out.println(value.getCity());
                System.out.println("*************");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        };
        StreamObserver<StudentRequest> studensWrapperByAgesRequest = serviceStub.getStudensWrapperByAges(studentResponseStreamObserver);
        studensWrapperByAgesRequest.onNext(StudentRequest.newBuilder().setAge(30).build());
        studensWrapperByAgesRequest.onNext(StudentRequest.newBuilder().setAge(40).build());
        studensWrapperByAgesRequest.onNext(StudentRequest.newBuilder().setAge(50).build());
        studensWrapperByAgesRequest.onNext(StudentRequest.newBuilder().setAge(60).build());
        studensWrapperByAgesRequest.onCompleted();
        //传输完成后要等待结果的返回
        Thread.sleep(3000);
    }



    public static void main(String[] args) throws InterruptedException {
        GrpcClient01 grpcClient01 = new GrpcClient01("localhost",50051);
        String name = "sansa";
        //grpcClient01.request(name);
        grpcClient01.biTalk();

    }
}
