/**
 * TODO濡沫系统平台<br/>
 * com.rumo.netty.websocket<br/>
 * WebSocketServer.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2018年9月19日-下午3:22:31 <br/>
 * 2018濡沫公司-版权所有<br/>
 */
package com.rumo.netty.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * WebSocketServer<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年9月19日-下午3:22:31 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Component
public class WebSocketSprngBootConfiguration implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent()==null) {
			try {
				WebSocketServer.getInstance().start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
