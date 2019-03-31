package com.rumo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.rumo.service.user.UserService;
import com.rumo.vo.ServerResponse2;

@Controller
public class LoginController {

	//添加了一些东西

	@Autowired
	private UserService userService;
	
	/**
	 * 跳转login页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	
	/**
	 * 登陆
	 * @param account
	 * @param password
	 * @return
	 */
	@ResponseBody
	@PostMapping("/logined")
	public ServerResponse2 logined(@RequestParam("account")String account,@RequestParam("password")String password,
			@RequestParam("code")String inputCode,
			@RequestParam("errorCount")Integer errorCount,
			HttpSession session) {
		//获取查询用户的数据，
		String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		ServerResponse2 serverResponse = userService.getLogin(account, password,sessionCode,inputCode,errorCount);
		if(serverResponse.getStatus()==200) {
			//把用户的数据放入到session中。
			session.setAttribute("session_user", serverResponse.getData());
			//最后的返回，不要把用户用户信息暴露出去，因为不安全。
			return ServerResponse2.createBySuccess();
		}else {
			//因为出错，本身 data就是null
			return serverResponse;
		}
		
	}
	
}
