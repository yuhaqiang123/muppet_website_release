package cn.bronzeware.muppet.websiterelease.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.bronzeware.muppet.core.Session;
import cn.bronzeware.muppet.core.SessionFactory;
import cn.bronzeware.muppet.transaction.Transaction;
import cn.bronzeware.muppet.transaction.TransactionExecute;


public class BaseDaoSupport<T> {

	@Autowired
	protected SessionFactory sessionFactory;
	
	
	public List<Map<String, Object>> queryForMap(String sql,Object[] params){
		Session session = sessionFactory.getSession();
		List<Map<String, Object>> results = session.query(sql, params);
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> queryForObject(final String sql, final Object[] params, final Class<T> clazz){
		List<T> results = null;
		results = (List) sessionFactory.transactionOperationCallback(new TransactionExecute() {
			public Object execute(Session session, Transaction transaction) {
				List<T> results = session.query(sql, params, clazz);
				return results;
			}
		});
		return results;
	}
	
	
	
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
