package com.bwallet.mappings.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserRequest implements Serializable {

	private static final long serialVersionUID = -248235716588125340L;

	private String userId;
	private String name;
	private String userName;
	private String pin;
	private String publicKey;
	private String privateKey;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	@Override
	public String toString() {
		return "UserRequest [userId=" + userId + ", name=" + name + ", publicKey=" + publicKey + "]";
	}
	
}
