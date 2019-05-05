package com.qezhhnjy.netty.first;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.Instant;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qezhhnjy
 * @date 2019/4/17-22:09
 */
@Slf4j
public class Tcp {

    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1";
        int port = 8080;
//        ExecutorService exec = Executors.newCachedThreadPool();

        ThreadPoolExecutor exec = new ThreadPoolExecutor(6, 6, 200,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("XX-task-%d")
                .build(), new ThreadPoolExecutor.AbortPolicy());
        exec.submit(new Server(port));

        for (int i = 0; i < 1; i++) {
            exec.submit(new Client(host, port));
        }
        TimeUnit.SECONDS.sleep(5);
    }

}

class PlainOioServer {
    public void serve(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                final Socket accept = serverSocket.accept();
                System.out.println("Accepted connection from " + accept);
                new Thread(() -> {
                    try (OutputStream out = accept.getOutputStream()) {
                        out.write("Hi!\r".getBytes(CharsetUtil.UTF_8));
                        out.flush();
                        accept.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PlainNioServer {
    public void serve(int port) {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            ServerSocket ssocket = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ssocket.bind(address);
            Selector selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
            for (; ; ) {
                selector.select();
                Set<SelectionKey> readKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = readKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("Accepted connection from " + client);
                    }
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NettyOioServer {
    public void server(int port) {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", CharsetUtil.UTF_8));
        OioEventLoopGroup group = new OioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {

                        @Override
                        protected void initChannel(io.netty.channel.socket.SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}

@Data
class HeartBeat {
    private short head = (short) 0xfeef;
    private short cmd = (short) 0xf001;
    private short id = 0x0001;
    private short len = 0x0004;
    private int time;
    private int data;
    private int check;
    private short tail = (short) 0xeffe;

    public HeartBeat(int count) {
        this.time = (int) Instant.now().getEpochSecond();
        this.data = count;
        this.check = head ^ data ^ tail;
    }

    public HeartBeat(ByteBuf buf) {
        head = buf.readShort();
        cmd = buf.readShort();
        id = buf.readShort();
        len = buf.readShort();
        time = buf.readInt();
        data = buf.readInt();
        check = buf.readInt();
        tail = buf.readShort();
    }

    public ByteBuf encode() {
        ByteBuf buf = Unpooled.buffer();
        buf.order();
        buf.writeShort(head);
        buf.writeShort(cmd);
        buf.writeShort(id);
        buf.writeShort(len);
        buf.writeInt(time);
        buf.writeInt(data);
        buf.writeInt(check);
        buf.writeShort(tail);
        return buf;
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
//                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(48, false, Unpooled.copiedBuffer("!"
//                                .getBytes())));
//                        ch.pipeline().addLast(new LineBasedFrameDecoder(48, true, true));
//                        ch.pipeline().addLast(new FixedLengthFrameDecoder(48));
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 6, 2, 10, 0, true));
//                        ch.pipeline().addLast(new StringDecoder());

                        ch.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {

                            private int count = 0;

                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                System.out.println(msg.toString());
                                System.out.println(new HeartBeat(msg));
                                System.out.println(count++);
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
//                        ch.pipeline().addLast(new StringEncoder());
//                        ch.pipeline().addLast(new ByteArrayEncoder());
                    }
                });

        try {
            Channel channel = bootstrap.connect(host, port).sync().channel();
            int count = 0;
            while (count < 30000) {
//                channel.writeAndFlush(Thread.currentThread().getName() + " " + LocalDateTime.now() + " : hello " +
//                        "world!");
                channel.writeAndFlush(new HeartBeat(count++).encode());
                System.out.println("count " + count);
//                channel.writeAndFlush(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
//                TimeUnit.MILLISECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


