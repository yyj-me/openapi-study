package org.thinker.oauth.provider.storage;

import org.thinker.oauth.provider.util.OAuthException;

import com.tfactory.oauth.OAuthTokenVO;

public interface OAuthStorage {
	
	public static final OAuthStorage instance = OAuthStorageImpl.getInstance();
	
	public void addOAuthToken(OAuthTokenVO newTokenVO)throws OAuthException;
	
	public OAuthTokenVO getOAuthTokenVO (String consumerKey)throws OAuthException;

	public OAuthTokenVO findOAuthTokenVOWithOauthToken(String oauth_token)throws OAuthException;

}
