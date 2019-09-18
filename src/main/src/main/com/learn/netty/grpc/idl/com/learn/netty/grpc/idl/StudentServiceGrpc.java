package com.learn.netty.grpc.idl;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *调用的接口
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.23.0)",
    comments = "Source: Hello.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "proto.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.MyRequest,
      com.learn.netty.grpc.idl.MyResponse> getGetRealNameByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUsername",
      requestType = com.learn.netty.grpc.idl.MyRequest.class,
      responseType = com.learn.netty.grpc.idl.MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.MyRequest,
      com.learn.netty.grpc.idl.MyResponse> getGetRealNameByUsernameMethod() {
    io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.MyRequest, com.learn.netty.grpc.idl.MyResponse> getGetRealNameByUsernameMethod;
    if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUsernameMethod = StudentServiceGrpc.getGetRealNameByUsernameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUsernameMethod = getGetRealNameByUsernameMethod =
              io.grpc.MethodDescriptor.<com.learn.netty.grpc.idl.MyRequest, com.learn.netty.grpc.idl.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRealNameByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.MyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUsername"))
              .build();
        }
      }
    }
    return getGetRealNameByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StudentRequest,
      com.learn.netty.grpc.idl.StudentResponse> getGetStudentByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentByAge",
      requestType = com.learn.netty.grpc.idl.StudentRequest.class,
      responseType = com.learn.netty.grpc.idl.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StudentRequest,
      com.learn.netty.grpc.idl.StudentResponse> getGetStudentByAgeMethod() {
    io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StudentRequest, com.learn.netty.grpc.idl.StudentResponse> getGetStudentByAgeMethod;
    if ((getGetStudentByAgeMethod = StudentServiceGrpc.getGetStudentByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentByAgeMethod = StudentServiceGrpc.getGetStudentByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentByAgeMethod = getGetStudentByAgeMethod =
              io.grpc.MethodDescriptor.<com.learn.netty.grpc.idl.StudentRequest, com.learn.netty.grpc.idl.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.StudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentByAge"))
              .build();
        }
      }
    }
    return getGetStudentByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StudentRequest,
      com.learn.netty.grpc.idl.StudentResponse> getGetStudensWrapperByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudensWrapperByAges",
      requestType = com.learn.netty.grpc.idl.StudentRequest.class,
      responseType = com.learn.netty.grpc.idl.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StudentRequest,
      com.learn.netty.grpc.idl.StudentResponse> getGetStudensWrapperByAgesMethod() {
    io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StudentRequest, com.learn.netty.grpc.idl.StudentResponse> getGetStudensWrapperByAgesMethod;
    if ((getGetStudensWrapperByAgesMethod = StudentServiceGrpc.getGetStudensWrapperByAgesMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudensWrapperByAgesMethod = StudentServiceGrpc.getGetStudensWrapperByAgesMethod) == null) {
          StudentServiceGrpc.getGetStudensWrapperByAgesMethod = getGetStudensWrapperByAgesMethod =
              io.grpc.MethodDescriptor.<com.learn.netty.grpc.idl.StudentRequest, com.learn.netty.grpc.idl.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudensWrapperByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.StudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudensWrapperByAges"))
              .build();
        }
      }
    }
    return getGetStudensWrapperByAgesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StreamRequest,
      com.learn.netty.grpc.idl.StreamResponse> getBiTalkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BiTalk",
      requestType = com.learn.netty.grpc.idl.StreamRequest.class,
      responseType = com.learn.netty.grpc.idl.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StreamRequest,
      com.learn.netty.grpc.idl.StreamResponse> getBiTalkMethod() {
    io.grpc.MethodDescriptor<com.learn.netty.grpc.idl.StreamRequest, com.learn.netty.grpc.idl.StreamResponse> getBiTalkMethod;
    if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
          StudentServiceGrpc.getBiTalkMethod = getBiTalkMethod =
              io.grpc.MethodDescriptor.<com.learn.netty.grpc.idl.StreamRequest, com.learn.netty.grpc.idl.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BiTalk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.learn.netty.grpc.idl.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("BiTalk"))
              .build();
        }
      }
    }
    return getBiTalkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   * <pre>
   *调用的接口
   * </pre>
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *发送一个数据，返回一个数据
     * </pre>
     */
    public void getRealNameByUsername(com.learn.netty.grpc.idl.MyRequest request,
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUsernameMethod(), responseObserver);
    }

    /**
     * <pre>
     *发送一个数据，返回一个流
     * </pre>
     */
    public void getStudentByAge(com.learn.netty.grpc.idl.StudentRequest request,
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStudentByAgeMethod(), responseObserver);
    }

    /**
     * <pre>
     *发送一个流，返回一个数据
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentRequest> getStudensWrapperByAges(
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudensWrapperByAgesMethod(), responseObserver);
    }

    /**
     * <pre>
     *发送一个流，接受一个流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBiTalkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUsernameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.learn.netty.grpc.idl.MyRequest,
                com.learn.netty.grpc.idl.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            getGetStudentByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.learn.netty.grpc.idl.StudentRequest,
                com.learn.netty.grpc.idl.StudentResponse>(
                  this, METHODID_GET_STUDENT_BY_AGE)))
          .addMethod(
            getGetStudensWrapperByAgesMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.learn.netty.grpc.idl.StudentRequest,
                com.learn.netty.grpc.idl.StudentResponse>(
                  this, METHODID_GET_STUDENS_WRAPPER_BY_AGES)))
          .addMethod(
            getBiTalkMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.learn.netty.grpc.idl.StreamRequest,
                com.learn.netty.grpc.idl.StreamResponse>(
                  this, METHODID_BI_TALK)))
          .build();
    }
  }

  /**
   * <pre>
   *调用的接口
   * </pre>
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送一个数据，返回一个数据
     * </pre>
     */
    public void getRealNameByUsername(com.learn.netty.grpc.idl.MyRequest request,
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *发送一个数据，返回一个流
     * </pre>
     */
    public void getStudentByAge(com.learn.netty.grpc.idl.StudentRequest request,
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetStudentByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *发送一个流，返回一个数据
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentRequest> getStudensWrapperByAges(
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetStudensWrapperByAgesMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *发送一个流，接受一个流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBiTalkMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *调用的接口
   * </pre>
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送一个数据，返回一个数据
     * </pre>
     */
    public com.learn.netty.grpc.idl.MyResponse getRealNameByUsername(com.learn.netty.grpc.idl.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUsernameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *发送一个数据，返回一个流
     * </pre>
     */
    public java.util.Iterator<com.learn.netty.grpc.idl.StudentResponse> getStudentByAge(
        com.learn.netty.grpc.idl.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetStudentByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *调用的接口
   * </pre>
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送一个数据，返回一个数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.learn.netty.grpc.idl.MyResponse> getRealNameByUsername(
        com.learn.netty.grpc.idl.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_STUDENT_BY_AGE = 1;
  private static final int METHODID_GET_STUDENS_WRAPPER_BY_AGES = 2;
  private static final int METHODID_BI_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.learn.netty.grpc.idl.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENT_BY_AGE:
          serviceImpl.getStudentByAge((com.learn.netty.grpc.idl.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STUDENS_WRAPPER_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudensWrapperByAges(
              (io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StudentResponse>) responseObserver);
        case METHODID_BI_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biTalk(
              (io.grpc.stub.StreamObserver<com.learn.netty.grpc.idl.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.learn.netty.grpc.idl.SutdentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUsernameMethod())
              .addMethod(getGetStudentByAgeMethod())
              .addMethod(getGetStudensWrapperByAgesMethod())
              .addMethod(getBiTalkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
