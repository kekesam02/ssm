package com.rumo.exception;
public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public GlobalException(String cm) {
		super(cm.toString());
	}
}