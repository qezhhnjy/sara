package com.qezhhnjy.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author qezhhnjy
 * @date 2019/4/17-23:50
 * 用于捕获{@link IdleState#WRITER_IDLE}事件(未在指定事件内向服务器发送数据),然后向<code>Server</code>端发送一个心跳包.
 */
public class ClientIdleStateTrigger extends ChannelInboundHandlerAdapter {

    public static final String HEART_BEAT = "heart beat!";

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush(HEART_BEAT);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
