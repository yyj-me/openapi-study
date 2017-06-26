package org.thinker.oauth.provider.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import org.thinker.oauth.provider.util.OAuthException;

public class HeaderParser extends OAuthParser  {
	
	public HeaderParser(String method, String requestURL, String data, String consumerSecretKey)throws OAuthException{

		this(method, requestURL, data, consumerSecretKey, null);
	
	}
	
	public HeaderParser(String method, String requestURL, String data, String consumerSecretKey, String oauthTokenSecret)throws OAuthException{


		this.dataMap = new TreeMap<String, String>();
		this.keyList = new ArrayList<String>();
		this.method = method;
		this.requestURL = requestURL;
		this.headerMsg = data;
		this.consumerSecretKey = consumerSecretKey;
		this.oauthTokenSecret = oauthTokenSecret;
		parsing();
	}
	
	

	
	/* (non-Javadoc)
	 * @see org.thinker.provider.parser.RequestParsing#parsing()
	 */
	@Override
	public void parsing()throws OAuthException{
		
		if(headerMsg == null || headerMsg.indexOf("OAuth")< 0){
			return ;
		}
		
		headerMsg = headerMsg.substring(5);
		
		
		//OAuth oauth_nonce="QP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk", oauth_callback="http%3A%2F%2Flocalhost%3A3005%2Fthe_dance%2Fprocess_callback%3Fservice_provider_id%3D11", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323042", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_signature="8wUi7m5HFQy76nowoCThusfgB%2BQ%3D", oauth_version="1.0"
		//POST&
		//https%3A%2F%2Fapi.twitter.com%2Foauth%2Frequest_token
		//&oauth_callback%3Dhttp%253A%252F%252Flocalhost%253A3005%252Fthe_dance%252Fprocess_callback%253Fservice_provider_id%253D11%26
		//oauth_consumer_key%3DGDdmIQH6jhtmLUypg82g%26
		//oauth_nonce%3DQP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk%26
		//oauth_signature_method%3DHMAC-SHA1%26
		//oauth_timestamp%3D1272323042%26
		//oauth_version%3D1.0
		
		String[] arr =  headerMsg.split(",");
		
		//System.out.println("HEADER DATA: "+Arrays.toString(arr));
		
		for(String token : arr){
			
			String[] keyAndValue = token.split("=");
			
			String value = keyAndValue[1].trim().substring(1);
			value = value.substring(0, value.length()-1);
			
			dataMap.put(keyAndValue[0].trim(), value);
			
			keyList.add(keyAndValue[0].trim());
			
		}
		
		
		if(dataMap.get("oauth_callback") != null){
			validateRequestToken();
		}else if (dataMap.get("oauth_verifier") != null){
			
			validateAccessToken();
		}else {
			
			validateResourceToken();
		}
		
		
	}
}
