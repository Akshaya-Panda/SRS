package com.example.demo.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "srs_tbl_user_credentials")
public class CredentialsBean {

	@Id
	private String userId;
	@Column
	private String password;
	@Column
	private String userType;
	@Column
	private int loginStatus;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		System.out.println("Setting the userid" + userId);
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("Setting the password" + password);
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Override
	public String toString() {
		return userId + " " + password + " " + userType + " " + loginStatus + "\n";
	}
}
