package com.sds.testprovider.util;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

import com.sds.testprovider.model.AccessTokenVO;
import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.model.UsersVO;

public class TokenGenerator {
	
	public static void generateConsumerKey(ConsumerVO consumerVO) throws Exception {		
		consumerVO.setConsumerKey(UUID.randomUUID().toString());
		String consumerSecret = DigestUtils.md5Hex(UUID.randomUUID().toString());
		consumerVO.setConsumerSecret(consumerSecret);
	}

	public static void generateRequestToken(RequestTokenVO requestTokenVO) throws Exception {
		if (requestTokenVO.getConsumerKey() == null) {
			throw new Exception("Consumer Key is null when creating request token!!");
		}
		
		String requestToken = UUID.randomUUID().toString();
		requestTokenVO.setRequestToken(requestToken);
		
        String requestTokenSecret = DigestUtils.md5Hex(UUID.randomUUID().toString());
        requestTokenVO.setRequestTokenSecret(requestTokenSecret);
        
        requestTokenVO.setVerifier( DigestUtils.md5Hex(UUID.randomUUID().toString()));
	}
	
	
	public static AccessTokenVO generateAccessToken(UsersVO usersVO, ConsumerVO consumerVO) {
		// AT : userno - MD5(userno & CK) ������ ǥ���� ����
		String tokenBase = usersVO.getUserno() + "&" + consumerVO.getConsumerKey();
		String accessToken = usersVO.getUserno() + "-" + DigestUtils.md5Hex(tokenBase);
		
		String accessTokenSecret = getAccessTokenSecret(usersVO.getPassword(), consumerVO.getConsumerSecret());
		return new AccessTokenVO(accessToken, accessTokenSecret, usersVO.getUserid(), usersVO.getUserno()); 
	}
	
	public static String getAccessTokenSecret(String password, String consumerSecret) {
		// ATS : MD5(pwd & CS) ������ ǥ���� ����
		String secretBase = password + "&" + consumerSecret;
		String accessTokenSecret = DigestUtils.md5Hex(secretBase);
		return accessTokenSecret;
	}
	
	
}