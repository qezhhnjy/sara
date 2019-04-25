package com.qezhhnjy.netty.first;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author qezhhnjy
 * @date 2019/4/17-22:09
 */
@Slf4j
public class Tcp {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;
        ExecutorService exec = Executors.newCachedThreadPool();

/*
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 5, 200,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("XX-task-%d")
                .build());
        executor.submit(new Server(port));
        executor.submit(new Client(host, port));
        executor.submit(new Client(host, port));
        executor.submit(new Client(host, port));
        executor.submit(new Client(host, port));
        executor.submit(new Client(host, port));
*/
        exec.submit(new Server(port));

        for (int i = 0; i < 100; i++) {
            exec.submit(new Client(host, port));
        }
    }

}

@Slf4j
@AllArgsConstructor
class Server implements Runnable {

    private int port;

    @Override
    public void run() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(boss, group).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                log.info("msg {}", msg);
                            }
                        });
                    }
                });
        try {
            server.bind(port).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            group.shutdownGracefully();
        }

    }
}

@Slf4j
@AllArgsConstructor
class Client implements Runnable {

    private String host;
    private int port;

    @Override
    public void run() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        try {
            Channel channel = bootstrap.connect(host, port).sync().channel();
            while (true) {
                channel.writeAndFlush(Thread.currentThread().getName() + " " + LocalDateTime.now() + " : hello world!");
//                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
