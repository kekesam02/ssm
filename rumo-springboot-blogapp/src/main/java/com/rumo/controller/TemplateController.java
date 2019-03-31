package com.rumo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumo.service.template.TemplateService;

@RestController
public class TemplateController implements ApplicationContextAware{

	/*@Autowired
	private TemplateService templateService;*/
	
	private ApplicationContext context;

	
	@GetMapping("/template")
	public String index(HttpServletRequest request) {
		System.out.println("***********************"+request.getServletContext().getRealPath("/"));
		return "你当前的模板是："+context.getBean(TemplateService.class).getTemplate();
	}
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	
	
}




















