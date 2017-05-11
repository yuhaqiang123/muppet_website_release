package cn.bronzeware.muppet.websiterelease.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import cn.bronzeware.muppet.websiterelease.common.MS;
import redis.clients.jedis.Jedis;

@Component
public class InternationalServiceSupport implements InternationalService{

	@Autowired
	private MessageSource ms;
	
	@Autowired
	private Jedis jedis;
	
	private String generateKey(String code, Locale locale){
		String key = "international:" + locale.getLanguage() + ":"+ code;
		return key;
	}
	
	public String getMessage(String code, Object[] params, Locale locale) {
		String msg = jedis.get(code);
		if(msg != null){
			return msg;
		}
		if(params == null){
			params = new Object[]{};
		}
		msg = ms.getMessage(code, params, locale);
		if(msg != null){
			String key = generateKey(code, locale);
			jedis.set(key, msg);
			jedis.expire(key, 60*60);
		}
		return null;
	}

	public String getMessage(String code, Locale locale) {
		String msg = ms.getMessage(code, new Object[]{},locale);
		return msg;
	}
	
	

	
	
}
