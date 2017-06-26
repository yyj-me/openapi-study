package org.thinker.oauth;

import java.util.Map;

public class AccessTokenVO extends RequestTokenVO  {

    private String screenName;
    private long userId;
    
    public AccessTokenVO(){
    	this(null);
    }
    
	public AccessTokenVO(String twitterMsg) {
		
		super();
		
		if(twitterMsg  == null){
			return;
		}
		
		Map<String, String> dataMap = OAuthUtil.setAccessData(twitterMsg);
		
		this.setRequestOauthToken(dataMap.get("oauth_token"));
		this.setVerifier(dataMap.get("oauth_verifier"));
		
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return super.toString()+",\nscreenName=" + screenName + ", \nuserId=" + userId;
	}
	
	@Override
	public void sign(){
		
		StringBuilder builder = new StringBuilder();
		builder.append(this.getMethod() +"&");
		builder.append(OAuthUtil.encode(this.getRequestURL())+"&");
		builder.append(OAuthUtil.encode("oauth_consumer_key="+OAuthUtil.encode(this.getConsumerKey())+"&"));
		builder.append(OAuthUtil.encode("oauth_nonce="+OAuthUtil.encode(this.getNonce())+"&"));
		builder.append(OAuthUtil.encode("oauth_signature_method="+OAuthUtil.encode(this.getSignatureMethod())+"&"));
		builder.append(OAuthUtil.encode("oauth_timestamp="+OAuthUtil.encode(this.getTimestamp())+"&"));
		builder.append(OAuthUtil.encode("oauth_token="+OAuthUtil.encode(this.getRequestOauthToken())+"&"));
		builder.append(OAuthUtil.encode("oauth_verifier="+OAuthUtil.encode(this.getVerifier())+"&"));
		builder.append(OAuthUtil.encode("oauth_version="+OAuthUtil.encode(this.getVersion())));
		
		HmacSHA1 sha1 = new HmacSHA1();
		
		System.out.println("client Consumer Secret key" + this.getConsumerSecretKey());
		System.out.println("client Consumer token Secret key" + this.getRequestOauthTokenSecret());
		
		sha1.setConsumerSecret(this.getConsumerSecretKey());
		sha1.setTokenSecret(this.getRequestOauthTokenSecret());
		
		try {
			String signed = sha1.getSignature(builder.toString());
			//System.out.println(signed);
			this.setSignature(signed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBaseString(builder.toString());
	}
	
	public static void main(String[] args) {
		
		AccessTokenVO vo = new AccessTokenVO();
		vo.setMethod("POST");
		vo.setRequestURL("https://api.twitter.com/oauth/access_token");
		
		vo.setConsumerKey("GDdmIQH6jhtmLUypg82g");
		vo.setConsumerSecretKey("MCD8BKwGdgPHvAuvgvz4EQpqDAtx89grbuNMRd7Eh98");
		vo.setNonce("9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8");
		vo.setTimestamp("1272323047");
		
		vo.setRequestOauthToken("8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc");
		vo.setRequestOauthTokenSecret("x6qpRnlEmW9JbQn4PQVVeVG8ZLPEx6A0TOebgwcuA");
		vo.setVerifier("pDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY");
		vo.sign();
		
		System.out.println(vo.getBaseString());
		//POST&https%3A%2F%2Fapi.twitter.com%2Foauth%2Faccess_token&oauth_consumer_key%3DGDdmIQH6jhtmLUypg82g%26oauth_nonce%3D9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1272323047%26oauth_token%3D8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc%26oauth_verifier%3DpDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY%26oauth_version%3D1.0
		//POST&https%3A%2F%2Fapi.twitter.com%2Foauth%2Faccess_token&oauth_consumer_key%3DGDdmIQH6jhtmLUypg82g%26oauth_nonce%3D9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1272323047%26oauth_token%3D8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc%26oauth_verifier%3DpDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY%26oauth_version%3D1.0
		
		System.out.println(vo.getSignature());
		//PUw/dHA4fnlJYM6RhXk5IU/0fCc=
	}
    
    
}
