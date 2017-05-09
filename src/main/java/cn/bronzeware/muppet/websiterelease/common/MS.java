package cn.bronzeware.muppet.websiterelease.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;


@Component
public class MS {

	@Autowired
	private MessageSource ms;
	
	private String languageTag;
	
	public  String getMessage(String code, HttpServletRequest request){
		RequestContext context = new RequestContext(request);
		return ms.getMessage(code, null, context.getLocale());
	}
	
	public String getMessage(String code, Object[] params, HttpServletRequest request){
		RequestContext context = new RequestContext(request);

		return ms.getMessage(code, params, request.getLocale());
	}
}
