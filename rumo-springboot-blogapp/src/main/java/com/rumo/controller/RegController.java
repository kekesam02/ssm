package com.rumo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rumo.service.user.UserService;
import com.rumo.vo.ServerResponse2;
import com.rumo.vo.UserVo;

@Controller
public class RegController {

	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转login页面
	 * @return
	 */
	@RequestMapping("/reg")
	public String reg() {
		return "reg";
	}
	
	
	@ResponseBody
	@RequestMapping("/reged")
	public ServerResponse2 reged(UserVo userVo) {
		return userService.saveUser(userVo);
	}
	

}
