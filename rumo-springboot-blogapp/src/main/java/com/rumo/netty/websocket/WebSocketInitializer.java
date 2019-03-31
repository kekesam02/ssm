/**
 * TODO濡沫系统平台<br/>
 * com.rumo.netty.websocket<br/>
 * WebSocketInitializer.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2018年9月19日-下午3:23:33 <br/>
 * 2018濡沫公司-版权所有<br/>
 */
package com.rumo.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 
 * WebSocketInitializer<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年9月19日-下午3:23:33 <br/>
 * @version 1.0.0<br/>
 * 
 */
public class WebSocketInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		//通过scoketchannel去获得对应的管道
		ChannelPipeline channelPipeline = channel.pipeline();
		
		//wesocket是基于http协议，所以需要有http编解码器
		channelPipeline.addLast(new HttpServerCodec());
		
		//处理大数据流或者进制文件的时候的优化处理和支持
		channelPipeline.addLast(new ChunkedWriteHandler());
		
		//添加 httpmessage的聚合器，专门去管理fullHttpRequest和FullHttpResponse。
		//几乎在netty中的编程，都会使用到此handler
		channelPipeline.addLast(new HttpObjectAggregator(1024*64));
		
		//=========================以上是支持http协议=================================
		
		//添加websocket服务协议的注册和支持，用于指定给客户端访问的理由：/ws
		//本handler会帮你去处理一些握手的动作，handshaking()此方法会包括一些：关闭，ping(请求) + pong(响应) = 心跳
		//对于websocket来讲，都是以frames为单位进行传输，不同的数据类型对应的frames也不同。
		channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		
		//添加自定义的handler来推送和发送客户端的处理类
		channelPipeline.addLast(new WebSocketChatHandler());
	}

}
