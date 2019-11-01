package com.pyt.demo.model;

public class CreateUserRequest {
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "CreateUserRequest [userName=" + userName + "]";
	}

}
