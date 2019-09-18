java.io中最核心的是stream，面向stream的编程，要么是inputStream，要么是OutputStream，不可能同时是两者
java.nio中有三个核心的概念
    Selector
    Channel
    Buffer
    面向块，或者面向缓冲区编程的。

    Buffer  <-- Channel         <-
    Buffer  <-- Channel         <-  Selector   ->  Thread
    Buffer  <-- Channel         <-

    一个线程可以在不同的Channel上切换，切换是通过事件来建立的


一.  Buffer(！线程不安全)

Buffer本身是一块内存，底层实现是一个数组，数据的 读写 都是通过Buffer实现的
Buffer提供了一种结构化的访问方式，并且可以追踪到系统的读写过程
Java中的8中数据类型都有不同的Buffer子类

类属性：
Capacity    ::容量
Position    ::读模式 记录读到的位置，写模式记录写到的位置
Limit       ::读模式，记录已经写入的东西有多少，写模式==Capacity

address     ：：只用于derect buffer 相对于另一种 HeapBuffer，不分配在堆，里面用于零拷贝/直接缓冲的功能，与IO设备打交道
                JVM中的数据如果和IO设备交互，一定要将jvm中的数据拷贝到管理的堆外然后再交互
                如果和IO设备交互的数据在JVM堆里面，如果在交互的时候，虚拟机发生了GC操作，就不能移动数据，这对于虚拟机来说不太现实，于是要把和IO设备交互的数据放在JVM管理的内存的外面


一些方法：
mark() 然后 reset()
put()   get()
rewind()    重读
clean()
isReadOnly()




二.  Channel
FileChannel ::用于文件操作    fileChannel.transferTo(0,channel.size(),socketChannel); 可以直接把文件放在socketChannel中传出去
ServerSocketChannel ::TCP服务端
SocketChannel   ：：TCP客户端
DatagramChannel ：：UDP操作

可以将Channel理解为io中的Stream，但是Channel可以是双向的，所以他可以更好的反应底层系统的情况，比如linux系统中底层操作系统通道就是双向的
所有数据的读写都是从Buffer操作的，永远不会直接操作Channel读写数据

△SocketChannel的创建方式：
    a,SocketChannel.open();
    b,ServerSocketChannel接收到链接的时候，会返回一个SocketChannel

三.  Selector：
△   传统的编程：
server:
    ServerSocket serverSocket = ..
    serverSocket.bind(8899)
    //死循环可以保证有多个客户端链接
    while(true){
        Socket socket = serverSocket.accept();
        //造出一个线程来处理对应的逻辑
        new Thread(socket).start();
    }
client:
    Socket socket = new Socket("localhost",8899);
    socket.connet();

△   问题：
每次连接都要启动一个线程，启动太多了，会崩了电脑

△   解决：
事件驱动的,服务器端只有一个线程

四.  一个完整的demo流程
服务端：
1.  定义一个Selector
    相当于一个跑腿的苦逼服务员，客人有要求就会告诉他，然后他传达客人的要求是什么
2.  定义一个服务端的通道，ServerSocketChannel，配置为非阻塞
    相当于一个大堂经理
3.  将通道注册到选择器上，serverSockeetChannel.regist(selector,OP_ACCEPT);
    相当于大堂经理告诉服务员，让他盯着是否有客人来饭店
4.  进入死循环，选择器   selector.select（）
    相当于服务器在大门口和客人面前轮询
5.  选择器如果选择出一个通道， selector.select()返回了一个值
    相当于客人有要求了
6.  根据选出来的通道的Key值，selector.selectionKeys()，返回一个集合，判断这个集合里面的key的类型
    △   如果是OP_ACCEPT类型的，ServerSocketChannel.accept()响应这个连接，并且将这个客人的SocketChannel保存下来，设置监听类型为OP_READ
        说明有新的客人来了，这时候，大堂经理来给客人安排一个座位，告诉服务员监听这个客人的点餐要求
    △   如果是OP_READ类型的，就安排对应的服务，比如转发信息，新开一个线程服务
        说明客人要点餐，这时候就要一个专门的点餐服务员给他服务
7.  选择器不停的选出有事件响应的通道
    跑腿服务员不停盯着各个顾客，不管是新来的，还是已经就坐的
8.  为OP_READ事件服务的线程工作完成之后就销毁掉
    点餐服务员给客人点餐结束后，就退出了
9.  选择器重复着选择的工作，不知道什么时候是个头