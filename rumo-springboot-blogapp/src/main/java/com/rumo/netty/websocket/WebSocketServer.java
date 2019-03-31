/**
 * TODO濡沫系统平台<br/>
 * com.rumo.netty.websocket<br/>
 * WebSocketServer.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2018年9月19日-下午3:22:31 <br/>
 * 2018濡沫公司-版权所有<br/>
 */
package com.rumo.netty.websocket;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * WebSocketServer<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年9月19日-下午3:22:31 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Component
public class WebSocketServer {
	
	
	private static class singleWebSocketServer {
		static final WebSocketServer instance = new WebSocketServer();
	}
	
	public static WebSocketServer getInstance() {
		return singleWebSocketServer.instance;
	}

	
	//定义个线程组0--用于接收客户端的链接，但是不做任何处理，根老板一样，不做事情的。
	private	EventLoopGroup eventLoopGroup;
	private	EventLoopGroup workLoopGroup;
	private	ServerBootstrap serverBootstrap;
	private	ChannelFuture channelFuture;
	
	
	
	public WebSocketServer() {
		//定义个线程组0--用于接收客户端的链接，但是不做任何处理，根老板一样，不做事情的。
		this.eventLoopGroup = new NioEventLoopGroup();
		//定义从线程组--老板线程组会任务分配给从线程，让手下线程组去执行任务。
		this.workLoopGroup = new NioEventLoopGroup();
		
		//启动netty服务。设置两个线程组--是一个启动类
		this.serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(eventLoopGroup,workLoopGroup)//设置主从线程组
		.channel(NioServerSocketChannel.class)//设置nio的双向通道
		.childHandler(new WebSocketInitializer()); //子处理器，用于处理workergroup
	}
	
	public void start() {
		//绑定端口，设置为同步的方式监听启动等待启动成功或者失败的信息
		this.channelFuture =  serverBootstrap.bind(8088);
		System.err.println("netty webscoket server 启动完毕，端口是："+8088);
	}
	
}
