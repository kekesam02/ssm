package com.rumo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rumo.service.blog.IBlogService;
import com.rumo.vo.ServerResponse2;

@Controller
public class IndexController {

	@Autowired
	private IBlogService blogService;
	
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@GetMapping("/blogs/{pageNo}/{pageSize}")
	public ServerResponse2 findBlogs(@PathVariable("pageNo")Integer pageNo,@PathVariable("pageSize")Integer pageSize,
			@RequestParam(value="k",required=false)String keyword,@RequestParam(value="s",required=false)String sort){
		return blogService.findBlogs(keyword,sort,pageNo, pageSize);
	}
	
	
	
	
}




















