package cn.bronzeware.muppet.websiterelease.common;

public class NetUtil {

	public static String ipConvert(Long num)   
	{  
	    String str = null;  
	    Long[] tt = new Long[4];  
	    tt[0] = (num >>> 24) >>> 0;  
	    tt[1] = ((num << 8) >>> 24) >>> 0;  
	    tt[2] = (num << 16) >>> 24;  
	    tt[3] = (num << 24) >>> 24;  
	    str = (tt[0]) + "." + (tt[1]) + "." + (tt[2]) + "." + (tt[3]);  
	    return str;  
	}
	
	 private static Long ipConvert(String ip) {    
	        Long ips = 0L;
	        String[] numbers = ip.split("\\.");  
	        //等价上面  
	        for (int i = 0; i < 4; ++i) {  
	            ips = ips << 8 | Integer.parseInt(numbers[i]);  
	        }  
	        return ips;     
	    }      
}
