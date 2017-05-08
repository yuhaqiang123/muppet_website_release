package cn.bronzeware.muppet.websiterelease.vo.cs;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class UserLoginVo {

	@NotNull(message="user.login.email.null")
	@Email(message="user.login.email.failed")
	private String email;
	
	@NotNull(message="user.login.password.null")
	private String encrptPassword;
	
	
	@NotNull(message="user.login.validateCode.null")
	private String validateCode;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncrptPassword() {
		return encrptPassword;
	}

	public void setEncrptPassword(String encrptPassword) {
		this.encrptPassword = encrptPassword;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	
	
}
