package com.rumo.service.user;

import com.rumo.vo.ServerResponse2;
import com.rumo.vo.UserVo;

public interface UserService {

	/**
	 * 根据账号和密码，查询用户是否存在
	 * @param account
	 * @param password
	 * @return
	 */
	public ServerResponse2 getLogin(String account,String password,String sessionCode,String inputCode,int errorCount);
	
	
	public ServerResponse2 saveUser(UserVo userVo);
}
