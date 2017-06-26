package org.thinker.oauth.generator;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.sds.testprovider.model.OAuthKeyVO;
import com.sds.testprovider.model.RequestTokenVO;

@Service("TokenGenerator")
public class TokenGenerator {
	
	public void generateConsumerKey(OAuthKeyVO oAuthKeyVO) throws Exception {
		oAuthKeyVO.setConsumerKey(UUID.randomUUID().toString());
		String consumerSecret = DigestUtils.md5Hex(UUID.randomUUID().toString());
		oAuthKeyVO.setConsumerSecret(consumerSecret);
		
		oAuthKeyVO.setOauthToken(UUID.randomUUID().toString());
		String oAuthTokenSecret = DigestUtils.md5Hex(UUID.randomUUID().toString());
		oAuthKeyVO.setOauthTokenSecret(oAuthTokenSecret);
	}

	public void generateRequestToken(RequestTokenVO requestTokenVO) throws Exception {
		if (requestTokenVO.getConsumerKey() == null) {
			throw new Exception("Consumer Key is null when creating request token!!");
		}
		
		String requestToken = UUID.randomUUID().toString();
		requestTokenVO.setRequestToken(requestToken);
		
        String requestTokenSecret = DigestUtils.md5Hex(UUID.randomUUID().toString());
        requestTokenVO.setRequestTokenSecret(requestTokenSecret);
        
        requestTokenVO.setVerifier( DigestUtils.md5Hex(System.currentTimeMillis()+""));
	}
	
}
