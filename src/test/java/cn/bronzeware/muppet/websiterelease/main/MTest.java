package cn.bronzeware.muppet.websiterelease.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MTest {

	
	public static void main(String[] args){
		
		ApplicationContext applicationContext = 
				new FileSystemXmlApplicationContext("classpath:applicationContext-mvc.xml");
		
		applicationContext.getBean("sessionFactory");
	}
}
