package com.rumo.vo;

/**
 * Created by geely enum类
 */
public enum ResponseCode {

	SUCCESS(200,"SUCCESS"),
    ERROR(400,"ERROR"),
    NEED_LOGIN(100,"NEED_LOGIN"),
    ERROR_LOGIN(401,"你输入的账号和密码有误!!"),
    ERROR_CODE_LOGIN(402,"你输入的验证有误!!"),
    ERROR_LIMIT_CODE(403,"出错超过5次数，30分钟之后在进行此操作！"),
    SESSION_FAIIL(101,"SESSION IS CLOSED"),
    ILLEGAL_ARGUMENT(202,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

	
	
	
	
	
	
}