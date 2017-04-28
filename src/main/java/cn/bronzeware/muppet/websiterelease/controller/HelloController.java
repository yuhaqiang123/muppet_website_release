package cn.bronzeware.muppet.websiterelease.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/muppet/hello")
public class HelloController {
	
	private Logger logger = Logger.getRootLogger();
	
	@RequestMapping(value="/index/name/{name}/msg/{msg}",method=RequestMethod.POST)
	public void index(@PathVariable String name
			, @PathVariable String msg
			, @CookieValue(value="Accept-Encoding") String encoding
			, @RequestBody String body
			){
		logger.info(String.format("name:%s, msg:%s, encoding:%s, body:%s ", name, msg, encoding, body));
	}

}
