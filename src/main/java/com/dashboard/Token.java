package com.dashboard;

public class Token {
	
	String email;
	String accessToken;
	Long accessTokenExpiry;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getAccessTokenExpiry() {
		return accessTokenExpiry;
	}
	public void setAccessTokenExpiry(Long accessTokenExpiry) {
		this.accessTokenExpiry = accessTokenExpiry;
	}
	
	public Token() {
		super();
	}
	
	public Token(String email, String accessToken) {
		super();
		this.email = email;
		this.accessToken = accessToken;
	}
	
	public Token(String email, String accessToken, Long accessTokenExpiry) {
		super();
		this.email = email;
		this.accessToken = accessToken;
		this.accessTokenExpiry = accessTokenExpiry;
	}

}
