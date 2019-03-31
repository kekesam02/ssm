package com.rumo.service.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.rumo.config.EmailConfig;
import com.rumo.pojo.User;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/*import freemarker.template.Template;
import freemarker.template.TemplateException;*/

@Service
public class EmailServiceImpl implements EmailService {

	
	@Autowired
	private EmailConfig emailConfig;
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@Async
	@Override
	public Future<String> sendTemplateEmail(User user,String title, String templateName) {
		try {
			//估计休眠几秒钟
			Thread.sleep(3000);
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
			messageHelper.setFrom(emailConfig.getEmailFrom());
			messageHelper.setTo(user.getEmail());
			messageHelper.setSubject(title);
			
			//设置附件
			//封装模板使用的数据
			Map<String, Object> root = new HashMap<>();
			root.put("title", title);
			root.put("email", user.getEmail());
			root.put("username", user.getUsername());
			root.put("password",user.getPassword());
			
			//List contents = null;
			//root.put("contents", contents);
			//在模板中用<#list contents as content>${content.title}</#list>
			
			//得到模板
			 try {
				 //读取模板
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
                try {
                	//将数据和模板中要动态替换的内容进行融合渲染，返回渲染以后的网页内容
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, root);
                    //写静态页面到服务器
                    //修改数据量，静态字段
                    messageHelper.setText(text, true);
                    mailSender.send(message);
                    return new AsyncResult<String>("success");
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}