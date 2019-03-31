//package com.rumo.job;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.ServletContext;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.ServletContextAware;
//
//import com.rumo.config.FreemakerContfiguration;
//
//@Component
//public class TaskJobConfiguration implements ServletContextAware {
//
//
//	@Autowired
//	private FreemakerContfiguration freemakerContfiguration;
//	
//	
//	private ServletContext appliction;
//	
//	@Value("${spring.rumo.template.path}")
//	private String templatePath;
//	
//	//
//	@Scheduled(cron="0/5 * * * * ? ")
//	public void createTask() {
//		String path = appliction.getRealPath("/");
//		//redis 存储源代码--linux 
//		String ymdPath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//		freemakerContfiguration.staticPage("", "template/index-five.html", path+"/resources/"+ymdPath,"index.html");
//		System.out.println("createTask========》五一到了");
//	}
//	
//	
//	/*@Scheduled(cron="0/10 * * * * ? ")
//	public void createTask2() {
//		freemakerContfiguration.staticPage("", "template/index-ten.html", templatePath+"index.html");
//		System.out.println("createTask2========》国庆到了");
//	}*/
//
//
//	@Override
//	public void setServletContext(ServletContext application) {
//		this.appliction = application;
//	}
//
//
//	
//	/*
//	 * 
//	//每年的五一执行一次
//	@Scheduled(cron="0 0 0 1 5 ? ")
//	public void createTask() {
//		freemakerContfiguration.staticPage(null, "template/index-five.html", "d://index.html");
//		System.out.println("每个五秒进入一次哦----");
//	}
//	
//	//每年的国庆执行一次
//	@Scheduled(cron="0 0 0 1 10 ? ")
//	public void createTask2() {
//		freemakerContfiguration.staticPage(null, "template/index-ten.html", "d://index.html");
//		System.out.println("每个五秒进入一次哦----");
//	}*/
//	
//}
