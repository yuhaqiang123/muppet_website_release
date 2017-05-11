package cn.bronzeware.muppet.websiterelease.controller;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestScope;

import cn.bronzeware.muppet.websiterelease.common.MS;
import cn.bronzeware.muppet.websiterelease.service.InternationalService;
import cn.bronzeware.muppet.websiterelease.transfer.AjaxResult;

@CrossOrigin(origins = "*", maxAge = 3600 /*, allowCredentials="true"*/,methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/international")
@Controller
public class InternationalController {
	
	
	@Autowired
	private MS ms;
	
	//日志记录
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private InternationalService is;
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult international(@RequestParam(required=false, defaultValue="error") String keys, HttpServletRequest request){		
		
		if(keys.equals("error")){
			return new AjaxResult(0, "error");
		}
		String[] keyArray = null;
		try{
			keyArray = keys.split(",");
		}catch(Exception e){
			return new AjaxResult(0, "error");
		}
		 
		Map<String, String> keyMap = new HashMap<String, String>();
		for(String key: keyArray){
			String msg = is.getMessage(key, ms.getLocale(request));
			keyMap.put(key, msg);
		}
		
		Map<String,Map> map = new HashMap<String,Map>(1);
		map.put("msg", keyMap);
		return new AjaxResult(1, "ok", map);
	}
	

}