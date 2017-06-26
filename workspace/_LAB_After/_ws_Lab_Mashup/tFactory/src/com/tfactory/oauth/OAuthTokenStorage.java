package com.tfactory.oauth;

import org.thinker.oauth.provider.util.OAuthException;

public interface OAuthTokenStorage {

	public void addNewTokens(OAuthTokenVO vo )throws OAuthException;
	
	public OAuthTokenVO getToken(String consumerKey)throws OAuthException;

	public OAuthTokenVO getTokenWithOAuthToken(String oauthToken)throws OAuthException;
	
	
}
