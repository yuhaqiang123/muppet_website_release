package cn.bronzeware.muppet.websiterelease.testcontroller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.http.Cookie;



//@TransactionConfiguration(defaultRollback=false)
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-mvc.xml")
@WebAppConfiguration
public class TestHelloController {

	 protected MockMvc mockMvc;

	   @Autowired
	   protected WebApplicationContext wac;
	
	
	 @Before()  //这个方法在每个方法执行之前都会执行一遍
	   public void setup() 
	   {
	       mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
	   }
	
	 @Test
	    public void testAdd() throws Exception {  
		 mockMvc.perform
         (MockMvcRequestBuilders.post("/muppet/hello/index/name/yuhaiqiang/msg/is ok")
             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
             .cookie(new Cookie("Accept-Encoding", "zh_CN"))
             .content("pasd=111")
             //.param("name","于海强").param("msg", "is ok")
             
         )
         .andExpect(status().isOk())    //返回的状态是200
         
         .andDo(print())        //打印出请求和相应的内容
         .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
	    }  
}
