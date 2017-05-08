package cn.bronzeware.muppet.websiterelease.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.bronzeware.muppet.util.log.Msg;
import cn.bronzeware.muppet.websiterelease.common.MS;
import cn.bronzeware.muppet.websiterelease.model.User;
import cn.bronzeware.muppet.websiterelease.service.UserService;
import cn.bronzeware.muppet.websiterelease.service.UserServiceSupport;
import cn.bronzeware.muppet.websiterelease.transfer.AjaxResult;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserLoginVo;
import cn.bronzeware.muppet.websiterelease.vo.cs.UserRegisterVo;
import cn.bronzeware.muppet.websiterelease.vo.sc.OnlineUserInfo;
import net.sf.json.JSONObject;


/***
 * <p>用户操作相关的Controller.
 * 
 * @author yuhaiqiang  yuhaiqiangvip@sina.com
 * @time 2017年5月8日 下午7:04:06
 * 
 * @see UserService
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private MS ms;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired()
	private UserService userService;

	/**
	 * <p>分解 {@link Errors},解析出验证失败的字段.
	 * @param ms
	 * @param errors
	 * @return
	 */
	private Map<String,Object> parseError(MS ms, Errors errors){
		if(errors == null || errors.hasErrors() == false){
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		for(FieldError error : errors.getFieldErrors()){
			map.put(error.getField(), ms.getMessage(error.getDefaultMessage(), null));
		}
		return map;
	}
	
	
	/**
	 * <p>判断邮箱是否被注册过.如果被注册,返回错误信息
	 * 要注意,即使验证邮箱尚未被验证,用户实际注册时,也需要
	 * @param email 被验证的email
	 * @return
	 */
	@RequestMapping("/email/validate/unique")
	public AjaxResult emailValidateUnique(@RequestBody(required=true) String email){
		boolean success = userService.emailUnique(email);
		
		Object[] param = {email};
		if(success){
			return new AjaxResult(1, "ok", ms.getMessage("user.register.email.unexisted", param));
		}else{
			return new AjaxResult(0, "error", ms.getMessage("user.register.email.existed", param));
		}
	}
	
	
	
	/**
	 * 用户注册服务
	 * nickName,email,password
	 * email地址必须唯一.
	 * @param userVo
	 * @param errors
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public AjaxResult userRegister(@Valid UserRegisterVo userVo, Errors errors){
		Object object = parseError(ms,errors);
		if(null != object){
			return new AjaxResult(0, ms.getMessage("user.register.failed") , object);
		}else{
			userService.register(userVo);
			Object[] params = {userVo.getNickName()};
			return new AjaxResult(1, "ok"
					, ms.getMessage("user.register.success",params));
		}
	}
	
	public AjaxResult userLogin(@Valid UserLoginVo userLogin, Errors errors){
		Object object = parseError(ms, errors);
		if(null != object){
			return new AjaxResult(0, ms.getMessage("user.login.failed"));
		}else{
			OnlineUserInfo info = userService.login(userLogin);
			if(info == null){
				return new AjaxResult(0, ms.getMessage("user.login.faild"));
			}else{
				return new AjaxResult(0, ms.getMessage("user.login.success"), info);
			}
		}		
	}

	
	
}
