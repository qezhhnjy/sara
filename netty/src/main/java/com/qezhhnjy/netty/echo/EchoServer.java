package com.qezhhnjy.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author qezhhnjy
 * @date 2019/4/21-22:25
 */
public class EchoServer {
    private final int port;


    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(8080).start();
    }

    private void start() throws InterruptedException {
        EchoServerHandler handler = new EchoServerHandler();
        // 1. 创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 2. 创建ServerBootStrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 3. 指定所使用的NIO传输Channel
            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    // 4. 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 5. 添加一个EchoServerHandler到子channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // EchoServerHandler 被标注为@Sharable,所以我们可以总是使用同样的实例
                            ch.pipeline().addLast(handler);
                        }
                    });
            // 异步地绑定服务器,调用sync()方法阻塞等待直到绑定完成.
            ChannelFuture future = bootstrap.bind().sync();
            // 获取Channel的CloseFuture,并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        }finally {
            //关闭 EventLoopGroup,释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}

