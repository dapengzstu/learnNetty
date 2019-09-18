这个是
gRPC使用的定义文件，gRPC默认的在proto文件夹下面的.proto编译成一个Java文件

在build.gradle文件中写入grpc对应的查件之后，右侧Gradle中->tasks->other->generateProto

然后运行 gradle generateProto

生成的文件在build->generated->source->proto->main下面两个文件夹 grpc/java，把他们拷贝到工程目录下面

