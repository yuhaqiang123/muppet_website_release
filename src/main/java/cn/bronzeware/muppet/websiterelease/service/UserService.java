package cn.bronzeware.muppet.websiterelease.service;

import cn.bronzeware.muppet.websiterelease.OperationException;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserLoginVo;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserRegisterVo;
import cn.bronzeware.muppet.websiterelease.vo.sc.OnlineUserInfo;


public interface UserService{

	public boolean register(UserRegisterVo user) ;
	
	public OnlineUserInfo login(UserLoginVo user) ;
	
	public boolean emailUnique(String email);
	
}
