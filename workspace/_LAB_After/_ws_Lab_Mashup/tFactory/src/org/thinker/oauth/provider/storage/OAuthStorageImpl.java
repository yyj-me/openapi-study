package org.thinker.oauth.provider.storage;

import java.util.LinkedList;
import java.util.List;

import org.thinker.oauth.provider.util.OAuthException;

import com.tfactory.oauth.OAuthTokenVO;

public class OAuthStorageImpl implements OAuthStorage {
	
	//private static final String path = OAuthStorageImpl.class.getResource("oauthTokens.dat").getFile();
	
	private List<OAuthTokenVO> list;
	
	private static OAuthStorageImpl instance = new OAuthStorageImpl();
	
	private OAuthStorageImpl(){
		
		list = new LinkedList<OAuthTokenVO>();
		
		//testData
		OAuthTokenVO vo = new OAuthTokenVO("86930565-119a-4ed4-977c-26f189d122871307023804954");
		vo.setConsumerSecretKey("f594b439a6fe594c5352656965297797");
		vo.setRequestToken("86930565-119a-4ed4-977c-26f189d1228713070238049541307023804959698000");
		vo.setRequestTokenSecret("b62fab0b60b8a2f1922918f68e2ddf99");
		vo.setAccessToken("f594b439a6fe594c53526569652977971307023804959849000");
		vo.setAccessTokenSecret("ade9e68e65d8416a4fa339c7f0d72099");
		vo.setVerifier("80d8580c48adca24a48db752150f417b");
		
		list.add(vo);
		
	}
	
	public static OAuthStorage getInstance(){
		return instance;
	}
	
	

	@Override
	public synchronized void addOAuthToken(OAuthTokenVO newTokenVO) throws OAuthException {
		
		if(list.contains(newTokenVO)){
			throw new OAuthException("Already existed Key");
		}
		
		list.add(newTokenVO);
		
		
	}

	@Override
	public synchronized OAuthTokenVO getOAuthTokenVO(String consumerKey)
			throws OAuthException {

		int idx = list.indexOf(new OAuthTokenVO(consumerKey));
		
		if(idx  < 0){
			throw new OAuthException("Cannot find Key");
		}
		
		return list.get(idx);
	}

	@Override
	public OAuthTokenVO findOAuthTokenVOWithOauthToken(String oauth_token) throws OAuthException{
		
		if(oauth_token == null){
			throw new OAuthException("Cannot find oauth key");
		}
		
		//change your way!
		for(OAuthTokenVO vo: list){
			if(oauth_token.equals(vo.getRequestToken())){
				return vo;
			}
		}
		return null;
	}

}
