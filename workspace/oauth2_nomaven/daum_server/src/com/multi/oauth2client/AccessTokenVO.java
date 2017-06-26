package com.multi.oauth2client;

import java.util.Arrays;

//{ "access_token":"36cce240502d310e804609433f07e1e81e7b6738",
//  "token_type":"bearer","expires_in":3600,
//  "refresh_token":"e0cc85bae752910ed9f21b8d7ccedc3f9f729e55",
//  "expires_in":1375700647394 }

public class AccessTokenVO {
	private String[] scopes;
	private String refresh_token;
	private String access_token;
	private long expires_in;
	private String token_type;

	public AccessTokenVO(String refresh_token, String access_token,
			long expires_in, String token_type, String state) {
		super();
		this.refresh_token = refresh_token;
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.token_type = token_type;
	}

	public AccessTokenVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String[] getScopes() {
		return scopes;
	}

	public void setScopes(String[] scopes) {
		this.scopes = scopes;
	}

	@Override
	public String toString() {
		return "AccessTokenVO [scopes=" + Arrays.toString(scopes) + ", refresh_token=" + refresh_token
				+ ", access_token=" + access_token + ", expires_in=" + expires_in + ", token_type=" + token_type + "]";
	}

	
	
}
