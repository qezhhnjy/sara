package com.qezhhnjy.netty.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author qezhhnjy
 * @date 2019/4/18-0:04
 * <p>客户端连接到服务器端后，会循环执行一个任务：随机等待几秒，然后ping一下Server端，即发送一个心跳包。</p>
 */
@Slf4j
public class Pinger extends ChannelInboundHandlerAdapter {

    private Random random = new Random();
    private int base = 8;
    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();
        ping(channel);
    }

    private void ping(Channel channel) {
        int max = Math.max(1, random.nextInt(base));
        log.info("next heart beat will send after {} s.", max);
        ScheduledFuture future = channel.eventLoop().schedule(() -> {
            if (channel.isActive()) {
                log.info("sending heart beat to the server ...");
                channel.writeAndFlush(ClientIdleStateTrigger.HEART_BEAT);
            } else {
                log.info("the connection has broken,cancel the heart beat task");
                channel.closeFuture();
                throw new RuntimeException();
            }
        }, max, TimeUnit.SECONDS);

        future.addListener((GenericFutureListener) future1 -> {
            if (future1.isSuccess()) {
                ping(channel);
            }
        });
    }
}
