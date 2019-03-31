package com.rumo.service.email;

import java.util.concurrent.Future;

import com.rumo.pojo.User;

public interface EmailService {

	//带附件的邮件发送
	public Future<String> sendTemplateEmail(User user,String title, String templateName);
}