<style>
h1,h2,h3,h4,h5,h6{
    font-weight:bold;
}
h1{
    color:#b43;
    font-size:60px;
}
h2{
    color:red;
    font-size:30px;
}
h3{
    color:#f88;
    font-size:24px;
}
h4{
    color:#abf;
    font-size:20px
}
h5{
    font-size:16px;
    color:#abf;
}
p.red{
    color:#dd6;
    font-weight:bold;
    font-size:16px;
}
</style>

 [Netty](#netty)
1. [第一部分 - Netty的概念及体系结构](#第一部分---netty的概念及体系结构)
    
    1.1 [第 1 章 Netty--异步和事件驱动](#第-1-章-netty--异步和事件驱动)
# Netty

## 第一部分 - Netty的概念及体系结构

<p class="red">Netty 是一款用于创建高性能网络应用程序的高级框架.</p>

### 第 1 章 Netty--异步和事件驱动

#### 1.1 Java网络编程

早期的Java API(java.net)只支持由本地系统套接字库提供的所谓的阻塞函数:
```java
int port = 8080;
try {
    ServerSocket serverSocket = new ServerSocket(port);
    Socket accept = serverSocket.accept();
    BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
    PrintWriter out = new PrintWriter(accept.getOutputStream(), true);
    String req, resp = "";
    while ((req = in.readLine()) != null) {
        if ("Done".equalsIgnoreCase(req)) {
            break;
        }
        out.println(resp);
    }
} catch (IOException e) {
    e.printStackTrace();
}
}
```

- `ServerSocket` 上的`accept()`方法将会一直阻塞到一个连接建立,随后返回一个新的`Socket`用于客户端和服务器之间的通信.该`ServerSocket`将继续监听传入的连接.

- `BufferReader` 和 `PrintWriter` 都衍生自 `Socket` 的输入输出流. 前者从一个字符输入流中读取文本,后者打印对象的格式化的表示到文本输出流.

- `readLine()`方法将会阻塞,直到一个由换行符或者回车符结尾的字符串被读取.

这段代码片段将只能同时处理一个连接，要管理多个并发客户端，需要为每个新的客户端
Socket 创建一个新的 Thread.

让我们考虑一下这种方案的影响:

第一，在任何
时候都可能有大量的线程处于休眠状态，只是等待输
入或者输出数据就绪，这可能算是一种资源浪费。

第
二，需要为每个线程的调用栈都分配内存，其默认值
大小区间为 64 KB 到 1 MB，具体取决于操作系统。

第
三，即使 Java 虚拟机（JVM）在物理上可以支持非常
大数量的线程，但是远在到达该极限之前，上下文切换所带来的开销就会带来麻烦，例如，在达
到 10 000 个连接的时候。
>##### Java NIO

>**新的还是非阻塞的**
>
>NIO 最开始是新的输入/输出（New Input/Output）的英文缩写，但是，该Java API 已经出现足够长的时间
了，不再是“新的”了，因此，如今大多数的用户认为NIO 代表非阻塞 I/O（Non-blocking I/O），而阻塞I/O（blocking
I/O）是旧的输入/输出（old input/output，OIO）。你也可能遇到它被称为普通I/O（plain I/O）的时候。

>##### 选择器