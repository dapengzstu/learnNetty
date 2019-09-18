client:stub
server:skeleton
序列化与反序列化：编码与解码
RPC：远程过程调用 remote procedure call
很多RPC框架是跨语言的，编写模式都是类似的
1.  需要定义一个接口说明文件IDL，描述了对象，属性，接口方法等一系列的信息
2.  通过RPC框架所提供的编译器，将接口说明文件编译成具体语言文件
3.  在客户端和服务器端分别引入RPC编译成的文件，即可调用远程方法，就像调用本地方法一样


步骤：
首先在官网找到zip，linux版本的压缩包，解压，配置到PATH运行protoc测试是否成功
然后在maven仓库里面找到，依赖，两个protobuf-java，protobuf-java-util
编写addressbook.proto
protoc --java_out=src/main/java  addressbook.proto  用这个命令编译，生成一个AddressBookProtos.java文件

protoc --java_out=src/main/java  src/main/java/com/learn/netty/protobufexample/addressbook.proto


一个proto程序，可以生成各种语言的代码，
