package org.thinker.oauth.provider.util;

import javax.servlet.ServletException;



public class OAuthException extends ServletException {

	private static final long serialVersionUID = 1L;

	public OAuthException(String msg){
		super(msg);
	}
	
	public OAuthException(Exception e){
		super(e);
	}

	

}
