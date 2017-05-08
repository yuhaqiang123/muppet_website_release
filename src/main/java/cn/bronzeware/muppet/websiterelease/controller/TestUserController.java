package cn.bronzeware.muppet.websiterelease.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.bronzeware.muppet.websiterelease.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-mvc.xml")
@WebAppConfiguration
public class TestUserController {
	 protected MockMvc mockMvc;

	   @Autowired
	   protected WebApplicationContext wac;
	
	
	 @Before()  //这个方法在每个方法执行之前都会执行一遍
	   public void setup() 
	   {
	       mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
	   }
	
	 @Test
	    public void testRegister() throws Exception {
		 User user = new User();
		 user.setEmail("1");
		 user.setEncrptPassword("2");
		 user.setId("2");
		 user.setNickName("1");
		 user.setRegisterIp(23323);
		 
		 ObjectMapper mapper = new ObjectMapper();
		 String reqUser = mapper.writeValueAsString(user);
		 mockMvc.perform
       (MockMvcRequestBuilders.post("/user/register")
           .contentType(MediaType.APPLICATION_JSON)
           .content(reqUser)
       )
       .andExpect(status().isOk())    //返回的状态是200
       .andDo(print())        //打印出请求和相应的内容
       .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
	    }  

}
