package com.rumo.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class FreemakerContfiguration {

	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	/*
	*//**
	 * 静态化首页方法
	 * @param data
	 * @return
	 */
	public String staticPage(Object data,String templateName,String targetPath,String filename) {
		//设置附件
		//封装模板使用的数据
		Map<String, Object> root = new HashMap<>();
		root.put("data", data);
		//得到模板
		 try {
			 //如果你分父目录不存在，递归创建
			 File parentPath = new File(targetPath);
			 if(!parentPath.exists())parentPath.mkdirs();
			 //组合最终的文件路径
			 File targetFile = new File(parentPath,filename);
			 //读取模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            try {
            	//将数据和模板中要动态替换的内容进行融合渲染，返回渲染以后的网页内容
                String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, root);
                FileUtils.write(targetFile, text,"UTF-8");
                return "index.html";
            } catch (TemplateException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
}
