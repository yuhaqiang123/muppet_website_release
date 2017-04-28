package cn.bronzeware.muppet.websiterelease;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:ApplicationContext*.xml")
public class TestSpring {

	Logger logger  = Logger.getRootLogger();
	@Test
	public void test(){
		//ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:ApplicationContext.xml");
		logger.info("ok");
	}
	
	@Before
	public void testBefore(){
		logger.info("test before");
	}
	
	@After
	public void testAfter(){
		logger.info("test after");
	}
	
}
