# Java - Socket应用

标签（空格分隔）： java socket 通信

---

## 第一章 网络基础知识
1. 网络基础知识
2. InetAddress类
3. URL
4. TCP编程

> **TCP/IP协议** 

TCP/IP是目前世界上应用最为广泛的协议，是以TCP和IP为基础的不同层次上多个协议的集合。
也称：TCP/IP协议族（栈）.

> **IP地址**

为实现网络中不同计算机之间的通信，每台机器都必须有一个唯一的表示。


> **端口**

1. 用于区分不同应用程序。
2. 端口号范围为0～65535,其中0～1023为系统所保留。
3. IP地址和端口号组成了所谓的Socket，Socket是网络上运行的程序之间双向通信链路的终结点，是TCP和UDP的基础。
4. **http:80 ftp:21 telnet:23**

> **Java中的网络支持**
针对网络通信的不同层次，Java提供的网络功能有四大类：

1. InetAddress：用于标识网络上的硬件资源。
2. URL：统一资源定位符，通过URL可以直接读取或写入网络上的数据。
3. Socket：使用TCP协议实现网络通信的Socket相关的类。
4. Datagram:使用UDP协议，将数据保存在数据报中，通过网络进行通信。

---
## 第二章 Java中网络相关API的应用
### 2.1 Java中InetAddress的应用
> **InetAddress类**

1. InetAddress类用于标识网络上的硬件资源，表示互联网协议（IP）地址。
```java
//获取本机的InetAddress实例
InetAddress localHost = InetAddress.getLocalHost();
System.out.println("计算机名：" + localHost.getHostName());
System.out.println("IP地址：" + localHost.getHostAddress());
byte[] address = localHost.getAddress();//获取字节数组形式的IP地址
System.out.println(Arrays.toString(address));
System.out.println(localHost);
System.out.println();
//根据机器名获取InetAddress实例
InetAddress byName = InetAddress.getByName("127.0.1.1");
//getByName("qezhhnjy-ThinkPad-E470c")
System.out.println("计算机名：" + byName.getHostName());
System.out.println("IP地址" + byName.getHostAddress());
```

### 2.2 Java中的URL的应用
> **URL**

1. URL(Uniform Resource Locator)统一资源定位符，表示Internet上某一资源的地址。
2. URL由两部分组成：协议名称和资源名称，中间用冒号分开。
3. 在java.net包中，提供了URL类来表示URL。

```java
//URL常用方法
//创建URL实例
//#号表示传递锚点信息。浏览器用于较长网页时定位到制定位置。
URL imooc = new URL("http://www.imooc.com");
URL url = new URL(imooc, "/index.html?username=qezhhnjy#test");
System.out.println(imooc);
System.out.println(url);
System.out.println(url.getProtocol());
System.out.println(url.getHost());
//没制定端口号，默认返回-1,但HTTP协议中会使用80端口。
System.out.println(url.getPort());
// /index.html
System.out.println("文件路径：" + url.getPath());
// /index.html?username=qezhhnjy
System.out.println("文件名：" + url.getFile());
// test
System.out.println("相对路径：" + url.getRef());
// username=qezhhnjy
System.out.println("查询字符串：" + url.getQuery());
```
> **使用URL读取网页内容**

1. 通过URL对象的openStream()方法可以得到指定资源的输入流。
2. 通过输入流可以读取，访问网络上的数据。

```java
//使用URL读取页面内容
URL baidu = new URL("http://www.baidu.com");
//通过openStream方法获取URL对象所表示的资源的字节输入流
InputStream inputStream = baidu.openStream();
//将字节输入流转换为字符输入流
InputStreamReader reader = new InputStreamReader(inputStream);
//为字符输入流添加缓冲
BufferedReader bufferedReader = new BufferedReader(reader);
//读取数据
String data = bufferedReader.readLine();
while(data!=null){
    System.out.println(data);
    System.out.println("------------------");
    data=bufferedReader.readLine();
}
inputStream.close();
reader.close();
bufferedReader.close();
```

---

## 第三章 通过Socket实现TCP编程
### 3.1 Socket简介
> **Socket通信**

TCP协议是面向链接，可靠的，有序的，以字节流的方式发送数据。
基于TCP协议实现网络通信的类

* 客户端的Socket类
* 服务器端的ServerSocket类

> **Socket通信模型**

> **Socket通信实现步骤**

1. 创建ServerSocket和Socket
2. 打开连接到Socket的输入/输出流
3. 按照协议对Socket进行读/写操作
4. 关闭输入输出流，关闭Socket

### 3.2 编程实现基于TCP的Socket通信

> 揭秘用户登录的故事

> **服务器端**

1. 创建ServerSocket对象，绑定监听端口
2. 通过accept()方法监听客户端请求
3. 连接建立后，通过输入流读取客户端发送的请求信息
4. 通过输出流向客户端发送响应信息
5. 关闭相关资源

> **客户端**

1. 创建Socket对象，指明需要连接的服务器的地址和端口号
2. 连接建立后，通过输出流向服务器端发送请求信息
3. 通过输入流获取服务器响应的信息
4. 关闭相关资源
```java
public class Server {
    public static void main(String[] args) {
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);
            //2.调用accept()方法开始监听，等待客户端的连接
            System.out.println("***服务器即将启动，等待客户端的连接");
            Socket accept = serverSocket.accept();
            //3.获取输入流，并读取客户端信息
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String info = null;
            while((info = bufferedReader.readLine())!=null){
                System.out.println("我是服务器，客户端说："+info);
            }
            //关闭输入流
            accept.shutdownInput();
            //4.关闭资源
            bufferedReader.close();
            serverSocket.close();
            accept.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

public class Client {
    public static void main(String[] args) {
        try {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("127.0.1.1",8888);
            //2.获取输出流，向服务器端发送信息
            OutputStream outputStream = socket.getOutputStream();
            //将输出流包装为打印流
            PrintWriter pw = new PrintWriter(outputStream);
            pw.write("用户名：qezhhnjy，密码：123");
            pw.flush();
            socket.shutdownOutput();
            pw.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
### 3.3 完善用户登录之服务器响应客户端
```java
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket通信服务端
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);
            //2.调用accept()方法开始监听，等待客户端的连接
            System.out.println("***服务器即将启动，等待客户端的连接");
            Socket accept = serverSocket.accept();
            //3.获取输入流，并读取客户端信息
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String info = null;
            while((info = bufferedReader.readLine())!=null){
                System.out.println("我是服务器，客户端说："+info);
            }
            //关闭输入流
            accept.shutdownInput();
            //4.获取输出流，响应客户端的请求
            OutputStream outputStream = accept.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            pw.write("欢迎您");
            pw.flush();
            accept.shutdownOutput();

            //5.关闭资源
            pw.close();
            outputStream.close();
            bufferedReader.close();
            accept.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

```java
import java.io.*;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现用户登录
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        try {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("127.0.1.1", 8888);
            //2.获取输出流，向服务器端发送信息
            OutputStream outputStream = socket.getOutputStream();
            //将输出流包装为打印流
            PrintWriter pw = new PrintWriter(outputStream);
            pw.write("用户名：qezhhnjy，密码：123");
            pw.flush();
            socket.shutdownOutput();
            //3.获取输入流，并读取服务器端的响应信息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info = null;
            while ((info = br.readLine()) != null)
                System.out.println("我是客户端，服务器说：" + info);
            socket.shutdownInput();
            //4.关闭资源
            br.close();
            pw.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.4 使用多线程实现多客户端的通信

> **多线程服务器**

**应用多线程来实现服务器与多客户端之间的通信**
**基本步骤**
1. 服务器端创建ServerSocket，循环调用accept()等待客户端连接
2. 客户端创建一个Socket并请求和服务器端连接
3. 服务器端接受客户端请求，创建Socket与该客户建立专线连接
4. 建立连接的两个Socket在一个单独的线程上对话
5. 服务器端继续等待新的连接

>**关于Socket is closed异常的出现原因**
>因为Socket会在IO流close的情况下自动关闭，所以如果想边发送边接受最正确的方式是在发送和接受的操作都做完后再一起关闭IO流。
```java
import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            pw.write("qezhhnjy" + Thread.currentThread());
            pw.flush();
            socket.shutdownOutput();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info = null;
            while ((info = bufferedReader.readLine()) != null)
                System.out.println(info);
            socket.shutdownInput();
            socket.close();
            outputStream.close();
            pw.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
```java
import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    //和本线程相关的Socket
    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    //线程执行的操作，响应客户端的请求
    public void run(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info = null;
            while((info=bufferedReader.readLine())!=null)
                System.out.println("接收到客户端信息:" + info);
            socket.shutdownInput();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            pw.write("欢迎"+socket.getInetAddress().getHostAddress()+Thread.currentThread());
            pw.flush();
            socket.shutdownOutput();
            bufferedReader.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
```java
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientThreadTest {
    public static void main(String[] args) {
        try {
            while (true) {
                new ClientThread(new Socket("127.0.0.1", 8866)).start();
                TimeUnit.MILLISECONDS.sleep(1500);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
```java
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;

public class ServerThreadTest {
    public static void main(String[] args) {
        try {
            System.out.println("ServerThreadTest.java开始接受通信");
            ServerSocket serverSocket = new ServerSocket(8866);
            while (true) {
                new ServerThread(serverSocket.accept()).start();
                TimeUnit.MILLISECONDS.sleep(1500);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 第四章 通过Socket实现UDP编程

### 4.1 DatagramPacket
> **UDP编程**

* UDP协议(用户数据报协议)是无连接，不可靠的，无序的，但更快一些。
* UDP协议以数据报为数据传输的载体。
* 进行数据传输时，首先需要将要传输的数据定义为数据报（Datagram），在数据报中指明数据所要达到的
Socket（主机地址和端口号），然后再将数据报发送出去。

> **相关操作类**

    DatagramPacket:表示数据报包
    DatagramSocket：进行端到端通信的类，此类表示用来发送和接收数据报包的套接字
    
### 4.2 编程实现基于UDP的Socket通信之服务器端

> **服务器端实现步骤**

1. 创建DatagramSocket，指定端口号
2. 创建DatagramPacket
3. 接收客户端发送的数据信息
4. 读取数据
  
> **客户端实现步骤**

1. 定义发送信息
2. 创建DatagramPacket，包含将要发送的信息
3. 创建DatagramSocket
4. 发送数据

```java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 服务器端，实现基于UDP的用户登录
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        /*
        接收客户端发送的数据
         */
        //1.创建服务器端DatagramSocket，指定端口
        DatagramSocket socket = new DatagramSocket(8800);
        //2.创建数据报，用于接受客户端发送的数据
        //创建字节数组，指定接受的数据包大小
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        //3.接收客户端发送的数据
        //此方法在接收到数据报之前会一直阻塞
        System.out.println("*****服务器端已启动");
        socket.receive(packet);
        //4.读取数据
        String info = new String(data, 0, packet.getLength());
        System.out.println("客户端说：" + info);


        /*
        向客户端响应数据
         */
        //1.定义客户端的地址，端口号，数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "欢迎您！".getBytes();
        //2.创建数据报，包含响应的数据信息
        DatagramPacket packet1 = new DatagramPacket(data2,data2.length,address,port);
        //3.响应客户端
        socket.send(packet1);
        //4.关闭资源
        socket.close();
    }
}
```
### 4.3 编程实现基于UDP的Socket通信之客户端
```java
import java.io.IOException;
import java.net.*;


/**
 * 客户端，实现基于UDP的用户登录
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {

        /*
        向服务器端发送数据
         */
        //1.定义服务器的地址，端口号，数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        byte[] data = "用户名：qezhhnjy，密码：123".getBytes();
        //2.创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data,data.length,address,port);
        //3.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();
        //4.向服务器端发送数据
        socket.send(packet);

        /*
        接收服务器端响应的数据
         */
        //1.创建数据报，用于接收服务器端响应的数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(data2,data2.length);
        //2.接收服务器响应的数据
        socket.receive(packet1);
        //3.读取数据
        String reply = new String(data2,0,packet1.getLength());
        System.out.println("接收到服务端响应：" + reply);
        //4.关闭资源
        socket.close();
    }
}
```

## 第五章 Socket总结
## 第六章 综合练习




