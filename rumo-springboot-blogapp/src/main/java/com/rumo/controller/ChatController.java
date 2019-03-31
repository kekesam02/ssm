package com.rumo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 防止Captcha机器人登陆
 * @author xuchengfei
 * 
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

	@RequestMapping
	public String chat() {
		return "chat";
	}
}