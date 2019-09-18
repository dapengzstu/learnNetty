1.  http是一种无状态，基于请求响应的协议
2.  不能建立长连接，需要不停的请求响应，传输了太多的http请求头，浪费资源
3.  websocket可以建立长链接，不用传输请求头，建立链接的时候，其实就是一个http协议，一旦链接建立好之后，在请求头里面携带的信息会让这个链接转换为一个长连接
    可以实现客户端和服务器端的全双工传输
4.  也可以用在非浏览器的场合，比如android或这ios上使用，实现手机和服务器端的长连接
5.  tomcat，spring，netty都对websocket支持


6.  netty传输都是以 frame 帧 形式传递的
7.  websocket访问的形式  ws://server:port/context_path
