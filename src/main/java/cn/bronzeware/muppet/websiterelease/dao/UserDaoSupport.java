package cn.bronzeware.muppet.websiterelease.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bronzeware.core.ioc.annotation.Component;
import cn.bronzeware.muppet.core.SessionFactory;
import cn.bronzeware.muppet.websiterelease.model.User;


@Repository
public class UserDaoSupport extends BaseDaoSupport<User>{

	public UserDaoSupport() {
		
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
}
	
