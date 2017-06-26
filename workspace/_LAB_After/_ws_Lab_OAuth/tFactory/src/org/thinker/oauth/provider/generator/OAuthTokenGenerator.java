package org.thinker.oauth.provider.generator;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.thinker.oauth.provider.util.HmacSHA1;
import org.thinker.oauth.provider.util.OAuthException;
import org.thinker.oauth.provider.util.OAuthUtil;

import com.tfactory.oauth.OAuthTokenVO;

public class OAuthTokenGenerator {
	
	
	public void generateTokens(OAuthTokenVO tokenVO)throws OAuthException{
		
		if(tokenVO.getConsumerKey() == null){
			throw new OAuthException("Consumer Key is null!");
		}
		
		String consumerSecretKey = DigestUtils.md5Hex(tokenVO.getConsumerKey());
		tokenVO.setConsumerSecretKey(consumerSecretKey);
		
		
        // for now use md5 of name + current time as token
        String requestToken = tokenVO.getConsumerKey() + System.nanoTime();
        
        tokenVO.setRequestToken(requestToken);
        
        //verifier token
        tokenVO.setVerifier( DigestUtils.md5Hex(System.currentTimeMillis()+""));
        
        
        String requestTokenSecret = DigestUtils.md5Hex(requestToken);
        // for now use md5 of name + current time + token as secret
        tokenVO.setRequestTokenSecret(requestTokenSecret);
        
        String accessToken = tokenVO.getConsumerSecretKey()+System.nanoTime();
        
        tokenVO.setAccessToken(accessToken);
        
        String accessTokenSecret =  DigestUtils.md5Hex(UUID.randomUUID().toString());
        
        tokenVO.setAccessTokenSecret(accessTokenSecret);
	}
	

	
}
