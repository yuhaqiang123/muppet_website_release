package cn.bronzeware.muppet.websiterelease.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.bronzeware.muppet.core.Session;
import cn.bronzeware.muppet.core.SessionFactory;
import cn.bronzeware.muppet.util.log.Logger;
import cn.bronzeware.muppet.websiterelease.model.User;

public class Main {

	
	public static void main(String[] args){
		
		ApplicationContext applicationContext = 
				new FileSystemXmlApplicationContext("classpath:applicationContext-mvc.xml");
		
		SessionFactory sessionFactory  = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session session = sessionFactory.getSession();
		Logger.println(session.queryById(User.class, 1));
		session.close();
	}
}
