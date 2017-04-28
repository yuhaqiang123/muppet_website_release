package cn.bronzeware.muppet.websiterelase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.bronzeware.muppet.core.Session;
import cn.bronzeware.muppet.core.SessionFactory;

@Component
public class BaseDaoSupport<T> {

	@Autowired
	protected SessionFactory sessionFactory;
	
	
	
	public boolean add(T t){
		Session session = sessionFactory.getSession();
		boolean result = session.insert(t);
		session.close();
		return result;
	}
	
	public boolean delete(T t){
		Session session = sessionFactory.getSession();
		boolean result = session.deleteByPrimaryKey(t);
		session.close();
		return result;
	}
	
	public boolean update(T t){
		Session session = sessionFactory.getSession();
		boolean result = session.updateByPrimaryKey(t);
		session.close();
		
		return result;
		
	}
	
	/*public T getById(Object primaryKey){
		Session session = sessionFactory.getSession();
		
		
	}*/
	
	
}
