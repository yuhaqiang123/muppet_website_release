package cn.bronzeware.muppet.websiterelease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.bronzeware.muppet.websiterelease.dao.UserDaoSupport;
import cn.bronzeware.muppet.websiterelease.model.User;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserLoginVo;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserRegisterVo;

@Component
public class UserServiceSupport implements UserService{

	public  UserServiceSupport() {
		
	}
	
	@Autowired
	private UserDaoSupport userDaoSupport;

	
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
	public boolean register(UserRegisterVo user) {
		
		return true;
	}


	/**
	 * 登录服务
	 */
	public boolean login(UserLoginVo user) {
		
		return true;
	}

}
