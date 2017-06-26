package com.tfactory.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thinker.oauth.provider.util.OAuthException;

public class AuthTokenHandler {
	
	private OAuthTokenStorage oauthStorage;
	
	public AuthTokenHandler(OAuthTokenStorage oauthStorage) {
		super();
		this.oauthStorage = oauthStorage;
	}
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws OAuthException {
		
		String oauth_token = request.getParameter("oauth_token");
		
		System.out.println("AuthorizeServlet :" + oauth_token);
		OAuthTokenVO tokenVO = oauthStorage.getTokenWithOAuthToken(oauth_token);
		
		String oauth_token_verifier = tokenVO.getVerifier();
		StringBuilder builder = new StringBuilder();
		
		builder.append("oauth_token=" + oauth_token +"&");
		builder.append("oauth_verifier=" + oauth_token_verifier);
		
		return builder.toString();
	}
}
