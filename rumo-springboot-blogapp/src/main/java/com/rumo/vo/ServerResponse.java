package com.rumo.vo;


/**
 * 
 * {
 *   data:
 *   status/code:
 *   msg:
 *   
 * }
 * 
 * 
 * 未来你技术 CTO 
 * 统一返回值
 * @author Administrator
 * @param <T>
 */
public class ServerResponse<T> {
	
	private int status;
	private String msg;
	private T data;
	
	//私有构造函数
	private ServerResponse(int status) {
		this.status = status;
	}
	
	//私有构造函数
	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
	
	//私有构造函数
	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	//私有构造函数
	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	
	
	// 使之不在json序列化结果当中
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	public int getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	//正确返回
	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<T>(Constants.SUCCESS_CODE);
	}

	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
		return new ServerResponse<T>(Constants.SUCCESS_CODE, msg);
	}

	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}
	
	
	//error返回
	public static <T> ServerResponse<T> createByError() {
		return new ServerResponse<T>(500, ResponseCode.ERROR.getDesc());
	}

	public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new ServerResponse<T>(errorCode, errorMessage);
	}
}
