package cn.bronzeware.muppet.websiterelease.service;

import cn.bronzeware.core.ioc.annotation.Autowired;
import cn.bronzeware.muppet.websiterelease.dao.BaseDaoSupport;

public class BaseServiceSupport<T> implements BaseService<T>{

	@Autowired
	private BaseDaoSupport<T> baseDaoSupport;
	
	public boolean add(T t) {
		return baseDaoSupport.add(t);
	}

	public boolean update(T t) {
		return baseDaoSupport.update(t);
	}

	public boolean deletebyId(Object primaryKey) {
		return false;
	}

	public T getById(Object primaryKeyValue) {
		return null;
	}

}
