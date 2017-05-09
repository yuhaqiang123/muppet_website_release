package cn.bronzeware.muppet.websiterelease.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.bronzeware.muppet.util.log.Msg;
import cn.bronzeware.muppet.websiterelease.common.MS;
import cn.bronzeware.muppet.websiterelease.common.NetUtil;
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
@CrossOrigin(origins = "*", maxAge = 3600 /*, allowCredentials="true"*/,methods = { RequestMethod.GET, RequestMethod.POST })
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
	private Map<String,Object> parseError(MS ms, Errors errors, HttpServletRequest request){
		if(errors == null || errors.hasErrors() == false){
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		for(FieldError error : errors.getFieldErrors()){
			map.put(error.getField(), ms.getMessage(error.getDefaultMessage(), null, request));
		}
		return map;
	}
	
	
	/**
	 * <p>判断邮箱是否被注册过.如果被注册,返回错误信息
	 * 要注意,即使验证邮箱尚未被验证,用户实际注册时,也需要
	 * @param email 被验证的email
	 * @return <p> 先验证邮箱.如果验证失败.
	 * 返回 status=0,msg=验证失败的消息
	 * <p> 如果邮箱不存在 那么返回 成功
	 * status=1,result=""
	 * <p>如果邮箱存在,那么返回 失败
	 * status=0, result=""
	 */
	@ResponseBody
	@RequestMapping("/email/validate/unique")
	public AjaxResult emailValidateUnique(@RequestBody(required=true)
				/*@Email(message="user.register.email.unlegal")*/ String email
				, Errors errors
				, HttpServletRequest request){
		Object object = parseError(ms,errors,request);
		if(null != object){
			return new AjaxResult(0, ms.getMessage("user.register.email.unlegal", request));
		}
		
		boolean success = userService.emailUnique(email);
		if(success){
			return new AjaxResult(1, ms.getMessage("user.register.email.unexisted", request));
		}else{
			return new AjaxResult(0, ms.getMessage("user.register.email.existed", request));
		}
	}
	
	/**
	 * <p>用户登录账户时需要获取验证码
	 * 获取并返回给前台,并在session保存.
	 * 
	 * <p> 之后可以考虑使用redis保存
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/apply/validatecode")
	public AjaxResult applyRegisterValidateCode(HttpSession session){
		String code = userService.generateValidateCode(session.getId());
		return new AjaxResult(1, "ok", code);
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
	public AjaxResult userRegister(@Valid UserRegisterVo userVo,
			@RequestParam(name="clientCode", required=true) String clientCode
			, Errors errors
			, HttpSession session
			,HttpServletRequest request){
		
		Map map = parseError(ms,errors,request);
		if(null != map){
			String code = userService.generateValidateCode(session.getId());
			map.put("validateCode", code);
			return new AjaxResult(0, ms.getMessage("user.register.failed",request) , map);
		}else{
			String serverCode = userService.removeValidateCode(session.getId());
			if(serverCode == null || !serverCode.equals(clientCode)){
				logger.debug(serverCode + ":" + clientCode);
				/**
				 * <p>服务器端验证码不存在,或过期,或不匹配
				 * 重新生成验证码,返回前端
				 */
				Map result = new HashMap<String,Object>();
				String code = userService.generateValidateCode(session.getId());
				result.put("validateCode", code);
				return new AjaxResult(2, ms.getMessage("user.validateCode.incorrect", request), result);
			}
			/**
			 * 设置Ip
			 */
			Long ip = NetUtil.getIpLong(request);
			userVo.setRegisterIp(ip);
			
			userService.register(userVo);
			return new AjaxResult(1
					, ms.getMessage("user.register.success", request));
		}
	}
	
	public AjaxResult userLogin(@Valid UserLoginVo userLogin, Errors errors, HttpServletRequest request){
		Object object = parseError(ms, errors, request);
		if(null != object){
			return new AjaxResult(0, ms.getMessage("user.login.failed", request));
		}else{
			OnlineUserInfo info = userService.login(userLogin);
			if(info == null){
				return new AjaxResult(0, ms.getMessage("user.login.faild", request));
			}else{
				return new AjaxResult(0, ms.getMessage("user.login.success", request), info);
			}
		}		
	}

	
	
}
