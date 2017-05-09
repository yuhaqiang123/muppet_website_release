package cn.bronzeware.muppet.websiterelease.common;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;


public class RedisUtil {

	private String url ;
	
	private volatile Jedis jedis = null;
	
	
	
	public  Jedis getInstance(){
		if(jedis == null){
			synchronized (jedis) {
				if(jedis == null){
					jedis = new Jedis(url);
				}
			}
		}
		return jedis;
	}
	
	public static void main(String[] args){
		
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
