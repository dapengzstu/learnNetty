
一.  字节流，字符流
ByteStream字节流（InputStream，OutputStream）   最顶层
CharacterStream字符流（Write，Read）依赖字节流        最顶层


读数据的逻辑：
open stream
while more information
read information
close the stream

写数据
open stream
while more information
write informatin
close the stream

读流和写流是两个流


二.  节点流，过滤流
节点流：从特定的地方读取数据，比如磁盘，内存
过滤流：使用节点流作为输入或输出

输入：节点流->过滤流->过滤流    Data
输出: Data 过滤流->过滤流 ->节点流
java的一种链接的机制，通过一个流和另一个流首尾向接，形成一个流管道，这是 一种Decorator（装饰）模式
通过流的链接，可以动态的增加流的一些功能

三.  具体的分类：

InputStream:
    节点流：
    FileInputStream         ::从一个文件中读取
    ByteArrayInputStream    ::从byte[]数组中读取
    ObjectInputStream       ::
    PipedInputStream        ::一个进程发送数据到管道中，另一个进程去读取
    SequenceInputStream     ::合并两个或者多个InputStream中
    StringBufferInputStream ::
    过滤流：
    FilterInputStream
        DataInputStream         ::从一个流中分析出 一些基本数据类型
        BufferdInputStream
        LinNumberInputStream    ::
        PushbackInputStream     ::从一个流中拿出一点东西看一下，是什么，然后可以放回去

OutputStream
    节点流：
    FileOutputStream
    ByteArrayOutputStream
    ObjectOutputStream
    PipedOutputStream
    过滤流:
    FilterOutputStream:
        DataOutputStream
        BufferedOutputStream    ：：将要写入的数据先写在内存里面，然后一次性的给他写入到磁盘中
        PrintStream

四.  装饰模式/包装模式

用一个装饰来包裹另一种类
new CCC(new BBB(new AAA)))

对客户端 透明 的方式扩展一个类的功能
是一种继承方式的替代
对用户来说并不会感觉装饰后和装饰前有什么不同
装饰模式可以在不创造更多的子类的情况下，将对象的功能扩展

装饰模式的角色：
1. (Inputstream) 抽象构件角色（Component）给出一个抽象接口，规范准备接受附加责任的对象
2. (节点流） 具体构件角色（Concrete Component)将要接受附加责任的具体类
3. FilterInputStream) 装饰角色（Decorator）持有一个构件（Component）对象的引用，定义与抽象构建接口一致的接口
4. (具体的过滤流） 具体装饰juese（Concrete Decorator）负责给构件对象贴上附加的责任

                            Component（接口，类）
   Decorator(过滤流，继承Component） <-------(被引用）  ConcretComponet（节点流，继承Componet）
   ConcreteDecorator（具体的过滤流继承）

装饰模式的特点：
装饰对象，真实对象，接口一样
装饰对象包含真实对象的引用
装饰对象讲请求转发给了真实对象
装饰对象在转发这些请求的前后添加一些功能

五.  一个例子
BufferedInputStream 继承了 FilterInputStream
    FilterInputStream 继承了 InputStream 里面有一个成员变量 InputStream
