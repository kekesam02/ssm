package com.rumo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController {
	
	@ResponseBody
	@GetMapping("/show")
	public String show() {
		System.out.println(5/0);
		return "success";
	}
}