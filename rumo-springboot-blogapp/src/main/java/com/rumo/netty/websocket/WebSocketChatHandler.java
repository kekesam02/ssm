/**
 * TODO濡沫系统平台<br/>
 * com.rumo.netty<br/>
 * HelloServerInitialzer.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2018年9月19日-下午2:29:32 <br/>
 * 2018濡沫公司-版权所有<br/>
 */
package com.rumo.netty.websocket;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 初始化器，channel主持胡，会执行里面的相应的初始化方法
 * HelloServerInitialzer<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年9月19日-下午2:29:32 <br/>
 * @version 1.0.0<br/>
 * 
 */
public class WebSocketChatHandler  extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	
	//用于记录和管理所有客户端的channel
	private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		//获取客户端传输的消息内容
		String message  = msg.text();
		System.out.println("接收到客户端的数据是：" + message);
		
		//数据推送给所有的websocket客户端
		/*for (Channel channel : clients) {
			channel.writeAndFlush(new TextWebSocketFrame("服务器接收时间："+LocalDateTime.now()+",消息的内容是："+message));
		}*/
		
		//此处和上面的循环是一致的
		clients.writeAndFlush(new TextWebSocketFrame("服务器接收时间："+LocalDateTime.now()+",发送消息的内容是："+message));
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().id().asLongText()+"有客户端添加进来了...................");
		clients.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//clients.remove(ctx.channel());
		System.out.println(ctx.channel().id().asLongText()+"有客户端离开了...................");
	}
	
	
	
}
