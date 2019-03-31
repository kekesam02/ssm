package com.rumo.service.user;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rumo.exception.GlobalException;
import com.rumo.mapper.UserMapper;
import com.rumo.pojo.User;
import com.rumo.redis.RedisService;
import com.rumo.redis.UserKey;
import com.rumo.service.email.EmailService;
import com.rumo.util.MD5Util;
import com.rumo.util.StringUtils;
import com.rumo.vo.ResponseCode;
import com.rumo.vo.ServerResponse2;
import com.rumo.vo.UserVo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 根据账号和密码，查询用户是否存在
	 * @param account
	 * @param password
	 * @return
	 *//*
	public ServerResponse2 getLogin(String account,String password,String sessionCode,String inputCode) {
		//判断账号密码是否输入
		if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),"请输入账号或者密码!!");
		}
		
		//根据账号和密码查询用户是否存在 123456
		////inputPassToFormPass +  formPassToDBPass = inputPassToDbPass(数据的最终密码)
		password = MD5Util.formPassToDBPass(password,MD5Util.salt);
		User user = userMapper.selectLogin(account, password);
		//如果不存在，返回错误状态吗401,并且提示
		if(user==null) {
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),ResponseCode.ERROR_LOGIN.getDesc() );
		}
		
		//如果成功返回正确
		//return ServerResponse2.createBySuccess();//200
		return ServerResponse2.createBySuccess(user);//200 user
	}*/
	
	
	//根据账号和密码去传递，性能还不+ account + password 进行索引优化
	/*public ServerResponse2 getLogin(String account,String password,String sessionCode,String inputCode,int errorCount) {
		//判断账号密码是否输入
		if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),"请输入账号或者密码!!");
		}
	
		//如果出错的次数>=3出现验证码的判断
		//查询redis中根 
		if(errorCount>=3) {
			//验证码判断
			if(StringUtils.isEmpty(inputCode)){
				return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_CODE_LOGIN.getCode(),"请输入验证码");
			}
			
			//判断两个验证码输入是否相同
			if(!sessionCode.equalsIgnoreCase(inputCode)) {
				return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_CODE_LOGIN.getCode(),ResponseCode.ERROR_CODE_LOGIN.getDesc());
			}
		}
		
		//根据手机号，查询用户信息，因为此时手机号码就是主键
		User user = userMapper.selectByPrimaryKey(new Long(account));
		//如果不存在，返回错误状态吗401,并且提示
		if(user==null) {
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),ResponseCode.ERROR_LOGIN.getDesc() );
		}
		
		//根据账号和密码查询用户是否存在 123456
		//inputPassToFormPass +  formPassToDBPass = inputPassToDbPass(数据的最终密码)
		password = MD5Util.formPassToDBPass(password,MD5Util.salt);
		if(!user.getPassword().equalsIgnoreCase(password)) {
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),ResponseCode.ERROR_LOGIN.getDesc() );
		}
		
		//如果成功返回正确
		//return ServerResponse2.createBySuccess();//200
		return ServerResponse2.createBySuccess(user);//200 user
	}*/
	
	
	public ServerResponse2 getLogin(String account,String password,String sessionCode,String inputCode,int errorCount) {
		//判断账号密码是否输入
		if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),"请输入账号或者密码!!");
		}
		
		//如果出错的次数>=5出现锁账号信息
		Long errorcount = redisService.get(UserKey.userCount, account, Long.class);
		if(errorcount !=null && errorcount>=5) {
			
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LIMIT_CODE);
		}
	
		//如果出错的次数>=3出现验证码的判断
		//查询redis中根 
		if(errorCount>=3) {
			//验证码判断
			if(StringUtils.isEmpty(inputCode)){
				return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_CODE_LOGIN.getCode(),"请输入验证码");
			}
			
			//判断两个验证码输入是否相同
			if(!sessionCode.equalsIgnoreCase(inputCode)) {
				return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_CODE_LOGIN.getCode(),ResponseCode.ERROR_CODE_LOGIN.getDesc());
			}
		}
		
		//根据手机号，查询用户信息，因为此时手机号码就是主键
		User user = userMapper.selectByPrimaryKey(new Long(account));
		//如果不存在，返回错误状态吗401,并且提示
		if(user==null) {
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),ResponseCode.ERROR_LOGIN.getDesc() );
		}
		
		//根据账号和密码查询用户是否存在 123456
		//inputPassToFormPass +  formPassToDBPass = inputPassToDbPass(数据的最终密码)
		password = MD5Util.formPassToDBPass(password,MD5Util.salt);
		if(!user.getPassword().equalsIgnoreCase(password)) {
			//进行计数操作
			long count = redisService.incr(UserKey.userCount, user.getId());
			if(count>=5) {
				//redisService.expire(UserKey.userCount, user.getId(),60*60*24);//24小时之后在执行
				redisService.expire(UserKey.userCount, user.getId(),60*30);//30分钟小时之后在执行
			}
			return ServerResponse2.createByErrorCodeMessage(ResponseCode.ERROR_LOGIN.getCode(),ResponseCode.ERROR_LOGIN.getDesc() );
		}
		
		//如果成功返回正确
		//return ServerResponse2.createBySuccess();//200
		return ServerResponse2.createBySuccess(user);//200 user
	}
	
	
	
	
	

	@Override
	@Transactional
	public ServerResponse2 saveUser(UserVo userVo) {
		
		if(userVo.getPhone()!=null) {
			throw new GlobalException("我出错了。。。。");
		}
		
		User user = new User();
		user.setId(new Long(userVo.getPhone()));
		user.setUsername(userVo.getUsername());
		user.setPassword(MD5Util.inputPassToDbPass(userVo.getPassword(), MD5Util.salt));
		user.setEmail(userVo.getEmail());
		user.setPhone(userVo.getPhone());
		//更新时间，创建时间
		int count = userMapper.insert(user);
		if(count>0) {
			//注册成功发送邮件，写入日志
			user.setPassword(userVo.getPassword());
			//发邮件很慢，异步处理，消息中间件.
			Future<String> result = emailService.sendTemplateEmail(user, "濡沫学院，注册成功提示", "/email/template.html");
			if(result.isDone()) {
				System.out.println("===========>邮件发送成功.....");
			}
			return ServerResponse2.createBySuccess();
		}else {
			return ServerResponse2.createByError();
		}
	}
}
