package cn.bronzeware.muppet.websiterelease.model;

import java.util.Date;

import cn.bronzeware.muppet.annotations.Column;
import cn.bronzeware.muppet.annotations.Table;
import cn.bronzeware.muppet.websiterelease.common.NetUtil;

@Table(tablename="tb_user")
public class User {

	@Column(columnname="user_id")
	private String id;
	
	@Column(columnname="nick_name")
	private String nickName;
	
	@Column(columnname="email")
	private String email;
	
	
	@Column(columnname="register_time")
	private Date registerTime;
	
	@Column(columnname="last_login_time")
	private Date lastLoginTime;
	
	@Column(columnname="register_ip")
	private long registerIp;
	
	@Column(columnname="encrpt_password")
	private String encrptPassword;

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickName=" + nickName + ", email=" + email + ", registerTime=" + registerTime
				+ ", lastLoginTime=" + lastLoginTime + ", registerIp=" + getRegisterIp() + ", encrptPassword="
				+ encrptPassword + "]";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	

	public String getRegisterIp() {
		return NetUtil.ipConvert(registerIp);
	}

	public void setRegisterIp(long registerIp) {
		this.registerIp = registerIp;
	}

	public String getEncrptPassword() {
		return encrptPassword;
	}

	public void setEncrptPassword(String encrptPassword) {
		this.encrptPassword = encrptPassword;
	}
	
}
