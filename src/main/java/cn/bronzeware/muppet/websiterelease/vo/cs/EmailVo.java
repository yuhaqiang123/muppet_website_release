package cn.bronzeware.muppet.websiterelease.vo.cs;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class EmailVo {

	@NotNull
	@Email(message="user.register.email.unlegal")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
