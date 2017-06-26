package org.thinker.oauth;


public class RequestTokenVO extends BasicTokenVO implements ISign{

	//result save 
	private String requestOauthToken;
	private String requestOauthTokenSecret;
	
	//request info
	private String requestURL;
	private String callbackURL;
	private String verifier;
	
	private String returnTo;
	

	private String baseString;
	
	
	

	public void setBaseString(String baseString) {
		this.baseString = baseString;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public String getBaseString() {
		return baseString;
	}
	public String getReturnTo() {
		return returnTo;
	}
	public void setReturnTo(String returnTo) {
		this.returnTo = returnTo;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getCallbackURL() {
		return callbackURL;
	}
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}
	public String getRequestOauthToken() {
		return requestOauthToken;
	}
	public void setRequestOauthToken(String requestOauthToken) {
		this.requestOauthToken = requestOauthToken;
	}
	public String getRequestOauthTokenSecret() {
		return requestOauthTokenSecret;
	}
	public void setRequestOauthTokenSecret(String requestOauthTokenSecret) {
		this.requestOauthTokenSecret = requestOauthTokenSecret;
	}

	
	
	@Override
	public String toString() {
		return "requestOauthToken=" + requestOauthToken
				+ ", \nrequestTokenSecret=" + requestOauthTokenSecret
				+ ", \nrequestURL=" + requestURL + ", \ncallbackURL="
				+ callbackURL + ", \nreturnTo=" + returnTo + ", \nbaseString="
				+ baseString;
	}
	
	@Override
	public void sign(){
		
		StringBuilder builder = new StringBuilder();
		builder.append(this.getMethod() +"&");
		builder.append(OAuthUtil.encode(requestURL)+"&");
		builder.append(OAuthUtil.encode("oauth_callback="+OAuthUtil.encode(callbackURL)+"&"));
		builder.append(OAuthUtil.encode("oauth_consumer_key="+OAuthUtil.encode(this.getConsumerKey())+"&"));
		builder.append(OAuthUtil.encode("oauth_nonce="+OAuthUtil.encode(this.getNonce())+"&"));
		builder.append(OAuthUtil.encode("oauth_signature_method="+OAuthUtil.encode(this.getSignatureMethod())+"&"));
		builder.append(OAuthUtil.encode("oauth_timestamp="+OAuthUtil.encode(this.getTimestamp())+"&"));
		builder.append(OAuthUtil.encode("oauth_version="+OAuthUtil.encode(this.getVersion())));
		
		HmacSHA1 sha1 = new HmacSHA1();
		sha1.setConsumerSecret(this.getConsumerSecretKey());
		sha1.setTokenSecret(null);
		
		try {
			String signed = sha1.getSignature(builder.toString());
			//System.out.println(signed);
			this.setSignature(signed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.baseString=builder.toString();
	}
	

	public static void main(String[] args) {
		
		RequestTokenVO vo = new RequestTokenVO();
		vo.setMethod("POST");
		vo.setRequestURL("https://api.twitter.com/oauth/request_token");
		vo.setCallbackURL("http://localhost:3005/the_dance/process_callback?service_provider_id=11");
		vo.setConsumerKey("GDdmIQH6jhtmLUypg82g");
		vo.setConsumerSecretKey("MCD8BKwGdgPHvAuvgvz4EQpqDAtx89grbuNMRd7Eh98");
		vo.setNonce("QP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk");
		vo.setTimestamp("1272323042");
		vo.sign();
		
		System.out.println(vo.getBaseString());
		System.out.println(vo.getSignature());
		
		//POST&https%3A%2F%2Fapi.twitter.com%2Foauth%2Frequest_token&oauth_callback%3Dhttp%253A%252F%252Flocalhost%253A3005%252Fthe_dance%252Fprocess_callback%253Fservice_provider_id%253D11%26oauth_consumer_key%3DGDdmIQH6jhtmLUypg82g%26oauth_nonce%3DQP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1272323042%26oauth_version%3D1.0
		//POST&https%3A%2F%2Fapi.twitter.com%2Foauth%2Frequest_token&oauth_callback%3Dhttp%253A%252F%252Flocalhost%253A3005%252Fthe_dance%252Fprocess_callback%253Fservice_provider_id%253D11%26oauth_consumer_key%3DGDdmIQH6jhtmLUypg82g%26oauth_nonce%3DQP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1272323042%26oauth_version%3D1.0
		//8wUi7m5HFQy76nowoCThusfgB+Q
	}

	
	
}
