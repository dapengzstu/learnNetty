这是用NIO写的一个React模式，是Netty的一个超简单版本
参考书籍：Scalable io in java
        Reactor


Reactor模式具体分析：
1.  Handle（句柄，描述符，事件产生的发源地，监听Handle）：是一种资源的表示，这种资源是由操作系统提供的，表示事件，比如linux文件描述符，socket描述符；分为外部事件（客户端链接请求，发送过来的数据）；内部事件（定时器事件）
2.  Synchronuous Event Demultiplexer(同步事件分离器）：本身是一个操作系统级别调用，在不同的操作系统上是不同的，用于等待事件（一个或多个）的发生，调用方在调用这个事件的时候会被阻塞，一直阻塞到同步事件分离器上有时间产生为止，才会返回。对于linux来说，同步事件分离器就是常用的I/O多路复用，比如select，poll，epoll。在javaNIO中对应的就是Selector上的select方法
3.  EventHandler（事件处理器）：本身是由回调方法构成，这种回调方法构成了与应用相关的对于某个事件的反馈机制，在Netty当中对事件处理器这个角色惊醒了一个升级，为开发者提供了大量的回调方法，供我们在特定的事件产生后实现响应的回调方法进行业务逻辑的处理
4.  ConcreteEventHandler（具体事件处理器）：继承了EventHandler，由应用开发这提供
5.  Initiation Dispatcher(初始分发器）：它实际上就是Reactor角色，定义了一写规范，这些规范用于控制事件的调度方式，同时提供了应用事件处理器的注册，删除等设施。是整个事件处理器的核心，通过同步事件分离器来等待事件的发生，一旦发生，就会分理处每一个事件，狨猴调用事件处理器，调用相关的的回调方法来处理这些事件。



流程：
1.  当应用向Initiation dispatcher注册具体的事件处理器的时候，应用会标识出该事件处理器希望Initiation Dispatcher在发生某个事件的时候通知该事件，该事件与Handle关联
2.  Initiation dispatcher会要求每个事件处理器向其传递他内部的Handle   （socket）   ，该Handle向操作系统标示了事件处理器
3.  当所有的事件处理器注册完毕之后，应用会调用handle_events（）方法来启动Initiaation Dispatcher的事件循环，这时，Initiation Dispatcher 会将每个注册事件管理器的handle合并起来，并使用同步事件分离器等待这些事件的发生。比如，TCP协议层使用select同步时间分离器操作来等待客户端发送的数据到达链接的socket handle上
4.  当某个事件源对于的Handle变为ready状态（TCP socket变为等待读取状态），同步事件分离器就会通知Initiation dispatcher
5.  Initiation dispatcher会触发这个事件处理器的回调方法，响应这个处于ready状态的Handle，当事件发生时候。Initiation dispatcher会将事件源激活的Handle作为key来寻找并分发恰当的事件处理器回调方法
6.  Initiation dispatcher会回调事件处理器的handle_events()方法执行特定功能。