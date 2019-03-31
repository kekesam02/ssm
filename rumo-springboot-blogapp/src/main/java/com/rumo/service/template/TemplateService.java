package com.rumo.service.template;

import org.springframework.stereotype.Service;

@Service
public class TemplateService {

	private String template ="index";

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
}
