package cn.bronzeware.muppet.websiterelease.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MS {

	@Autowired
	private MessageSource ms;
	
	private String languageTag;
	
	private Locale locale = Locale.getDefault();
	
	public String getLanguageTag() {
		return languageTag;
	}

	
	public void setLanguageTag(String languageTag) {
		this.languageTag = languageTag;
		Locale.setDefault(Locale.forLanguageTag(languageTag));
		locale = Locale.getDefault();
	}

	public  String getMessage(String code){
		Locale.forLanguageTag(languageTag);
		return ms.getMessage(code, null, locale);
	}
	
	public String getMessage(String code, Object[] params){
		return ms.getMessage(code, params, locale);
	}
}
