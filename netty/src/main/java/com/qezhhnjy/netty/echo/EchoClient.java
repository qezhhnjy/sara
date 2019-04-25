package com.qezhhnjy.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author qezhhnjy
 * @date 2019/4/21-23:08
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("127.0.0.1", 8080).start();
    }

    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private int id;
    private int age;
    private int gender;
    private int phone;
    private String name;

    public byte[] encode() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(id);
        buffer.putInt(age);
        buffer.putInt(gender);
        buffer.putInt(phone);
        buffer.put(name.getBytes());
        int position = buffer.position();
        buffer.flip();
        byte[] bytes = new byte[position];
        buffer.get(bytes);
        return bytes;
    }

    public void decode(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(bytes);
        buffer.flip();
        id = buffer.getInt();
        age = buffer.getInt();
        gender = buffer.getInt();
        phone = buffer.getInt();
        byte[] bs = new byte[buffer.limit() - buffer.position()];
        buffer.get(bs);
        name = new String(bs);
    }
}
