package org.thinker.oauth.provider.parser;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.thinker.oauth.provider.util.HmacSHA1;
import org.thinker.oauth.provider.util.OAuthException;
import org.thinker.oauth.provider.util.OAuthUtil;

public abstract class OAuthParser implements RequestParsing{

	protected Map<String, String> dataMap;
	protected List<String> keyList;
	protected String headerMsg;
	protected String method;
	protected String requestURL;
	private String baseString;
	private String signature;
	private String oauthToken;
	protected String oauthTokenSecret;
	protected String consumerSecretKey;

	public static  String findHeaderString(String headerMsg, String key)
			throws OAuthException {
				
				
		
				if(headerMsg == null || headerMsg.indexOf("OAuth")< 0){
					return null;
				}
				
				headerMsg = headerMsg.substring(5);
				
				
				
				String[] arr =  headerMsg.split(",");
				
				//System.out.println(Arrays.toString(arr));
				
				for(String token : arr){
					
					String[] keyAndValue = token.split("=");
					
					//System.out.println(Arrays.toString(keyAndValue));
					
					if(key.equals(keyAndValue[0].trim())){
						
						String value = keyAndValue[1].trim().substring(1);
						value = value.substring(0, value.length()-1);
						return value;
					}
					
								
				}
				return null;
			}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(TreeMap<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	public String getHeaderMsg() {
		return headerMsg;
	}

	public void setHeaderMsg(String headerMsg) {
		this.headerMsg = headerMsg;
	}

	public void validateResourceToken() throws OAuthException {
		
		
		validateAccessToken();
	}

	protected void validateAccessToken() throws OAuthException{
		
		makeBaseString();
		
		HmacSHA1 sha1 = new HmacSHA1();
		sha1.setConsumerSecret(this.consumerSecretKey);
		
		sha1.setTokenSecret(this.oauthTokenSecret);
		
		
		boolean valid = false;
		try {
			
			System.out.println("SIGNATUER: "+OAuthUtil.decode(this.signature));
			System.out.println("BASESTRING: "+ baseString);		
			System.out.println("TEST: " + sha1.getSignature(baseString));
			
			valid = sha1.isValid(OAuthUtil.decode(this.signature), baseString);
			
			if(valid == false){
				throw new OAuthException("Invaild  Token");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OAuthException("Unavailable Token");
		}
		
		System.out.println(valid);
		
		
	}

	public void validateRequestToken() throws OAuthException {
			
			//POST&http%3A%2F%2Flocalhost%3A8080%2FtProvider%2Frequest_token&oauth_callback%3Dhttp%253A%252F%252F127.0.0.1%253A8080%252Ftwit%252Fcallback%26oauth_consumer_key%3DGRiVVLOyXaEkf8gOolw0A%26oauth_nonce%3D1306731068602548000%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1306731068%26oauth_version%3D1.0
			//POST&http%3A%2F%2Flocalhost%3A8080%2FtProvider%2Frequest_token&oauth_callback%3Dhttp%253A%252F%252F127.0.0.1%253A8080%252Ftwit%252Fcallback%26oauth_consumer_key%3DGRiVVLOyXaEkf8gOolw0A%26oauth_nonce%3D1306731068602548000%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1306731068%26oauth_version%3D1.0
	
			makeBaseString();
			
			HmacSHA1 sha1 = new HmacSHA1();
			sha1.setConsumerSecret(this.consumerSecretKey);
			
			sha1.setTokenSecret(null);
			
			
			boolean valid = false;
			try {
				
	//			System.out.println("SIGNATUER: "+OAuthUtil.decode(this.signature));
	//			System.out.println("BASESTRING: "+ baseString);		
	//			System.out.println("TEST: " + sha1.getSignature(baseString));
				
				valid = sha1.isValid(OAuthUtil.decode(this.signature), baseString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(valid);
		}

	private void makeBaseString() {
		StringBuilder builder = new StringBuilder();
		builder.append(method +"&");
		builder.append(OAuthUtil.encode(requestURL)+"&");
		
		

		Iterator<String> it = dataMap.keySet().iterator();
		
		int size = dataMap.size();
		
		int i = 0; 
		
		while(it.hasNext()){
			
			i++;
			
			String key = it.next();
			
			//System.out.println("MakeBaseString KEY: " + key);
			
			if(key.indexOf("oauth_signature")>=0 &&  key.indexOf("oauth_signature_method")< 0 ){
				this.signature = dataMap.get(key);
				continue;
			}
			
			
			builder.append ( key +"%3D" + OAuthUtil.encode(dataMap.get(key)) );
			
			if( i != size){
				builder.append("%26");
			}
			
			
		}
		
		
		baseString = builder.toString();
		
		//System.out.println(baseString);
		//System.out.println(this.signature);
	}



	public OAuthParser() {
		super();
	}

}