package com.appspot.omega;

public class LoginToken {
	String access_token;
	String token_type;
	int expires_in;
	String id_token;
	public LoginToken(String access_token, String token_type, int expires_in,
			String id_token) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.expires_in = expires_in;
		this.id_token = id_token;
	}
	public LoginToken() {
		// TODO Auto-generated constructor stub
	}
}
