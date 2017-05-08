package cn.bronzeware.muppet.websiterelease.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.bronzeware.muppet.core.Session;
import cn.bronzeware.muppet.core.SessionFactory;


public class BaseDaoSupport<T> {

	@Autowired
	protected SessionFactory sessionFactory;
	
	
	
	public boolean add(T t){
		Session session = sessionFactory.getSession();
		boolean result = session.insert(t);
		session.close();
		return result;
	}
	
	public boolean deleteByPrimaryKey(T t){
		Session session = sessionFactory.getSession();
		//boolean result = session.deleteByPrimaryKey(t);
		session.close();
		return false;
	}
	
	public boolean update(T t){
		Session session = sessionFactory.getSession();
		boolean result = session.updateByPrimaryKey(t);
		session.close();
		
		return result;
		
	}
	
	public T getById(Class clazz,Object primaryKey){
		Session session = sessionFactory.getSession();
		T t = (T) session.queryById(clazz, primaryKey);
		session.close();
		return t;
	}
	
	
}
