package com.qezhhnjy.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.DatagramPacketEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qezhhnjy
 * @date 2019/4/25-21:07
 */
public class First {

    public static final int A = 8888;
    public static final int B = 9999;

    public static void main(String[] args) {
        new Thread(() -> client(A, new Person(Integer.MAX_VALUE, Integer.MIN_VALUE, 1))).start();
        new Thread(() -> server(B)).start();
    }

    public static void client(int port, Person person) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new DatagramPacketEncoder<>(new PersonEncoder()));

            Channel channel = bootstrap.bind(port).sync().channel();
            InetSocketAddress a = new InetSocketAddress("127.0.0.1", A);
            InetSocketAddress b = new InetSocketAddress("127.0.0.1", B);
            while (true) {
                channel.writeAndFlush(new DefaultAddressedEnvelope<>(person, b, a));
//                channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(person.encoder()), b));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void server(int port) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new PersonDecoder());

            bootstrap.bind(port).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}

@AllArgsConstructor
@Data
class Person {

    private int id;
    private int password;
    private long time;

    public Person(ByteBuffer buffer) {
        buffer.flip();
        this.id = buffer.getInt();
        this.password = buffer.getInt();
        this.time = buffer.getLong();
    }

    public ByteBuffer encoder() {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.putInt(id);
        allocate.putInt(password);
        allocate.putLong(Instant.now().getEpochSecond());
        allocate.flip();
        return allocate;
    }

}

class PersonEncoder extends MessageToMessageEncoder<Person> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Person msg, List<Object> out) throws Exception {
        ByteBuffer encoder = msg.encoder();
        out.add(Unpooled.copiedBuffer(encoder));
    }
}

class PersonDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        System.out.println(msg.recipient());
        System.out.println(msg.sender());
        ByteBuf content = msg.content();
        int i = content.readableBytes();
        ByteBuffer allocate = ByteBuffer.allocate(i);
        content.readBytes(allocate);
        Person person = new Person(allocate);
        System.out.println(person);
    }
}
