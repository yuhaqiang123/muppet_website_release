package cn.bronzeware.muppet.websiterelease.service;



public interface BaseService<T>{
	
	public boolean add(T t);
	
	public boolean update(T t);
	
	public boolean deletebyId(Object primaryKey);
	
	public T getById(Object primaryKeyValue);

}
