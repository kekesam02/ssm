package com.rumo.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rumo.vo.ServerResponse2;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ResponseBody
	@ExceptionHandler(value=Exception.class)
	public ServerResponse2 exceptionHandler(HttpServletRequest request, Exception e){
		e.printStackTrace();
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return ServerResponse2.createByErrorCodeMessage(500, ex.getMessage());
		}else if(e instanceof BindException) {
			BindException ex = (BindException)e;
			List<ObjectError> errors = ex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return ServerResponse2.createByErrorCodeMessage(500, msg);
		}else {
			return ServerResponse2.createByErrorCodeMessage(500, "服务器出错了");
		}
	}
}