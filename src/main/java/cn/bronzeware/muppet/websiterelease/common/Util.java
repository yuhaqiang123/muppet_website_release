package cn.bronzeware.muppet.websiterelease.common;

import java.nio.charset.Charset;
import java.util.Base64;

public class Util {

	
	private static Charset UTF8 = null;
	
	static{
		UTF8 = Charset.forName("UTF-8");
	}
	public static String encrptPassword(String password){
		return Base64.getEncoder().encodeToString(password.getBytes(UTF8));
	}
}
