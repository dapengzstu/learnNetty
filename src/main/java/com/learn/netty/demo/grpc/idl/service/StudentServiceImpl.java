package com.learn.netty.demo.grpc.idl.service;


import com.learn.netty.demo.grpc.idl.*;
import io.grpc.stub.StreamObserver;

/**
 *  这个类继承了自动生成的类，通过重写这个类的方法，实现service的方法
 * */

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    /**
    *   发送一个数据，返回一个数据
    * */
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("accept the message from client:  " + request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver)  {
        responseObserver.onNext(StudentResponse.newBuilder().setName("张四").setAge(12).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张五").setAge(12).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张流").setAge(12).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张七").setAge(12).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张吧").setAge(12).setCity("北京").build());


        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudensWrapperByAges(StreamObserver<StudentResponse> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                //对每个发来的消息，做处理
                int age = value.getAge();
                System.out.println(age);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("error");
            }

            @Override
            public void onCompleted() {

                StudentResponse black = StudentResponse.newBuilder().setName("后端黑无常").setCity("地府").setAge(17000).build();
                responseObserver.onNext(black);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());

                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(value.getRequestInfo() + " + response").build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("bitTalk error");
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
