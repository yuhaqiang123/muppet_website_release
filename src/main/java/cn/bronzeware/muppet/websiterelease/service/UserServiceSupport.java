package cn.bronzeware.muppet.websiterelease.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.bronzeware.muppet.websiterelease.common.Util;
import cn.bronzeware.muppet.websiterelease.dao.UserDaoSupport;
import cn.bronzeware.muppet.websiterelease.model.User;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserLoginVo;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserRegisterVo;
import cn.bronzeware.muppet.websiterelease.vo.sc.OnlineUserInfo;
import redis.clients.jedis.Jedis;

@Component
public class UserServiceSupport implements UserService{

	public  UserServiceSupport() {
		
	}
	
	@Autowired
	private UserDaoSupport userDaoSupport;

	@Autowired
	private Jedis jedis;
	
	private static final String validateCodeSuffix = "validateCode";
	
	public String getValidateCode(String sessionId){
		sessionId += validateCodeSuffix;
		String code = jedis.get(sessionId);
		return code;
	}
	
	public String generateValidateCode(String sessionId){
		String code = UUID.randomUUID().toString().substring(0, 4);
		sessionId += validateCodeSuffix;
		jedis.set(sessionId, code);
		jedis.expire(sessionId, 60 * 5);
		return code;
	}
	
	/***
	 * <p>删除指定 SESSIONID 的 验证码.并返回查找到的验证码
	 * 
	 * @see #getValidateCode(String)
	 * @return 如果相关验证码存在,那么返回其值,否则返回null
	 */
	public String removeValidateCode(String sessionId){
		String code = this.getValidateCode(sessionId);
		sessionId += validateCodeSuffix;
		return jedis.del(sessionId) > 0 ? code : null;
	}
	
	
	
	/**
	 * 验证邮箱是否唯一
	 * @param email
	 * @return
	 */
	public boolean emailUnique(String email){
		return true;
	}
	
	
	/***
	 * <p>用户注册服务.
	 * 需要检查邮箱名是否存在,如果存在,则不能注册.
	 * 如果不存在,即注册用户.
	 * 
	 */
	public boolean register(UserRegisterVo userVo) {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setEmail(userVo.getEmail());
		user.setRegisterIp(userVo.getRegisterIp());
		String password = userVo.getPassword();
		user.setEncrptPassword(Util.encrptPassword(password));
		user.setRegisterTime(new Date(System.currentTimeMillis()));
		user.setLastLoginTime(new Date(System.currentTimeMillis()));
		user.setNickName(userVo.getEmail());
		return userDaoSupport.add(user);
	}


	/**
	 * 登录服务
	 */
	public OnlineUserInfo login(UserLoginVo user) {
		
		return null;
	}

}
