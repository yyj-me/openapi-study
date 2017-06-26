package org.thinker.oauth;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class TokenSender {
	
	private HttpClient httpClient;
	
	public static final int TYPE_PARAM = 0;
	public static final int TYPE_HEADER = 1;
	
	private int type;
	
	public TokenSender(){
		
		this(TYPE_PARAM);
	}
	
	public TokenSender(int type){
		this.type = type;
		this.httpClient = new HttpClient();
	}
	
	
	public void sendHttpClient(BasicTokenVO vo)throws Exception{
		
		System.out.println("sendHttpClient ");
		
		
		String responseText = null;
		
		if(vo.getClass().getName().equals("org.thinker.oauth.RequestTokenVO")){
			
			responseText = sendToRequest(type, (RequestTokenVO) vo);
			
			//System.out.println(responseText);
				
			saveResultToRequestTokenVO(responseText, (RequestTokenVO)vo);
			
		}else if(vo.getClass().getName().equals("org.thinker.oauth.AccessTokenVO")){
			
			responseText = sendToAccess(type, (AccessTokenVO) vo);
			
			//System.out.println(responseText);
			//oauth_token=165407408-Vn2DOR7KxIYriKs1lTTx0pBbiE2ElvHKKYveQRPe&oauth_token_secret=kemBOsNsJkpqvJg4u78HAyOG5PbitZLvXeUBa06Lc&user_id=165407408&screen_name=thinkingCookie
			//saveResultToTokenVO(responseText, (RequestTokenVO)vo);
			saveResultToAccessTokenVO(responseText, (AccessTokenVO)vo);
			
		}else if(vo.getClass().getName().equals("org.thinker.oauth.ResourceTokenVO")){
			
			responseText  = sendToResource(type, (ResourceTokenVO)vo);
			
			//System.out.println(responseText);
			
			saveResultResourceTokenVO(responseText, (ResourceTokenVO)vo);
		}
	}
	
	




	private void saveResultResourceTokenVO(String responseText,
			ResourceTokenVO vo) throws Exception{
		
		vo.setResult(responseText);
		
	}

	protected String sendToRequest(int type2, RequestTokenVO vo)throws Exception {
		
		if(type2 == TYPE_PARAM){
			return requestRequestTokenWithParamType(vo);
		}else if(type2 == TYPE_HEADER){
			return requestRequestTokenWithHeaderType(vo);
		}
		
		return null;
	}
	
	protected String sendToAccess(int type2, AccessTokenVO vo) throws Exception{
		
		if(type2 == TYPE_PARAM){
			return requestAccessTokenWithParamType(vo);
		}else if(type2 == TYPE_HEADER){
			return requestAccessTokenWithHeaderType(vo);
		}
		
		return null;
	}
	
	protected String sendToResource(int type2, ResourceTokenVO vo)throws Exception {
		
		if(type2 == TYPE_PARAM){
			return requestResourceTokenWithParamType(vo);
		}else if(type2 == TYPE_HEADER){
			return requestResourceTokenWithHeaderType(vo);
		}
		
		return null;
	}
	
	



	public void saveResultToRequestTokenVO(String responseText, RequestTokenVO vo) throws Exception{
		
		
		//System.out.println("response text: " + responseText);
		
		String[] tokens = responseText.split("&");
		
		//oauth_token=8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc&oauth_token_secret=x6qpRnlEmW9JbQn4PQVVeVG8ZLPEx6A0TOebgwcuA&oauth_callback_confirmed=true
		
		for(String token : tokens){
			
			String[] keyValue = token.split("=");
			//oauth_token
			if(keyValue[0].equals("oauth_token")){
				vo.setRequestOauthToken(keyValue[1]);
			}else if(keyValue[0].equals("oauth_token_secret")){
				vo.setRequestOauthTokenSecret(keyValue[1]);
			}else if(keyValue[0].equals("oauth_verifier")){
				vo.setVerifier(keyValue[1]);
			}
		}
	}
	
	private void saveResultToAccessTokenVO(String responseText,
			AccessTokenVO vo) throws Exception{
		// oauth_token=165407408-Vn2DOR7KxIYriKs1lTTx0pBbiE2ElvHKKYveQRPe&oauth_token_secret=kemBOsNsJkpqvJg4u78HAyOG5PbitZLvXeUBa06Lc&user_id=165407408&screen_name=thinkingCookie
		String[] tokens = responseText.split("&");
		

		
		for(String token : tokens){
			
			String[] keyValue = token.split("=");
			//oauth_token
			if(keyValue[0].equals("oauth_token")){
				vo.setRequestOauthToken(keyValue[1]);
			}else if(keyValue[0].equals("oauth_token_secret")){
				vo.setRequestOauthTokenSecret(keyValue[1]);
			}else if(keyValue[0].equals("oauth_verifier")){
				vo.setVerifier(keyValue[1]);
			}else if(keyValue[0].equals("user_id")){
				vo.setUserId(Long.parseLong(keyValue[1]));
			}else if(keyValue[0].equals("screen_name")){
				vo.setScreenName(keyValue[1]);
			}
		}
		
	}
	
	protected String requestAccessTokenWithParamType(RequestTokenVO vo)throws Exception {

		String result = null;
		

				
		return result;
	}
	
	
	protected String requestAccessTokenWithHeaderType(AccessTokenVO vo) throws Exception{
		
		System.out.println("requestAccessTokenWithHeaderType");
		
		String result = null;

		HttpMethod method = null;
		
		if(vo.getMethod().equals("GET")){
			method = new GetMethod(vo.getRequestURL());
			
		}else if(vo.getMethod().equals("POST")){
			method = new PostMethod(vo.getRequestURL());
			
		}
		
		method.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		//OAuth oauth_nonce="QP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk", oauth_callback="http%3A%2F%2Flocalhost%3A3005%2Fthe_dance%2Fprocess_callback%3Fservice_provider_id%3D11", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323042", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_signature="8wUi7m5HFQy76nowoCThusfgB%2BQ%3D", oauth_version="1.0"
		//OAuth oauth_nonce="QP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk", oauth_callback="http%3A%2F%2Flocalhost%3A3005%2Fthe_dance%2Fprocess_callback%3Fservice_provider_id%3D11", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323042", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_signature="8wUi7m5HFQy76nowoCThusfgB%2BQ%3D", oauth_version="1.0"
		
		Header header = new Header();

		header.setName("Authorization");
		
		StringBuilder builder = new StringBuilder();
		builder.append("OAuth");
		builder.append(" oauth_nonce=\"" + vo.getNonce()+"\",");

		builder.append(" oauth_signature_method=\"" + vo.getSignatureMethod()+"\",");
		builder.append(" oauth_timestamp=\"" + vo.getTimestamp()+"\",");
		builder.append(" oauth_consumer_key=\"" + vo.getConsumerKey()+"\",");
		builder.append(" oauth_token=\"" + OAuthUtil.encode(vo.getRequestOauthToken())+"\",");
		
		builder.append(" oauth_verifier=\"" + OAuthUtil.encode(vo.getVerifier())+"\",");
		builder.append(" oauth_signature=\"" + OAuthUtil.encode(vo.getSignature())+"\",");
		builder.append(" oauth_version=\"" + vo.getVersion()+"\"");
		
		header.setValue(builder.toString());
		
		method.addRequestHeader(header);
		
		

		httpClient.executeMethod(method);
		result = method.getResponseBodyAsString();
		
		return result;
	}
	
	
	


	protected String requestRequestTokenWithParamType(RequestTokenVO vo) throws Exception{

		String result = null;
		

				
		return result;
	}
	
	protected String requestRequestTokenWithHeaderType(RequestTokenVO vo)throws Exception {
		
		System.out.println("requestRequestTokenWithHeader " + vo.getRequestURL());
		
		String result = null;

		HttpMethod method = null;
		
		if(vo.getMethod().equals("GET")){
			method = new GetMethod(vo.getRequestURL());
			
		}else if(vo.getMethod().equals("POST")){
			method = new PostMethod(vo.getRequestURL());
			
		}
		
		method.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		//OAuth oauth_nonce="QP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk", oauth_callback="http%3A%2F%2Flocalhost%3A3005%2Fthe_dance%2Fprocess_callback%3Fservice_provider_id%3D11", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323042", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_signature="8wUi7m5HFQy76nowoCThusfgB%2BQ%3D", oauth_version="1.0"
		//OAuth oauth_nonce="QP70eNmVz8jvdPevU3oJD2AfF7R7odC2XJcn4XlZJqk", oauth_callback="http%3A%2F%2Flocalhost%3A3005%2Fthe_dance%2Fprocess_callback%3Fservice_provider_id%3D11", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323042", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_signature="8wUi7m5HFQy76nowoCThusfgB%2BQ%3D", oauth_version="1.0"
		
		Header header = new Header();

		header.setName("Authorization");
		
		StringBuilder builder = new StringBuilder();
		builder.append("OAuth");
		builder.append(" oauth_nonce=\"" + vo.getNonce()+"\",");
		builder.append(" oauth_callback=\"" + OAuthUtil.encode(vo.getCallbackURL())+"\",");
		builder.append(" oauth_signature_method=\"" + vo.getSignatureMethod()+"\",");
		builder.append(" oauth_timestamp=\"" + vo.getTimestamp()+"\",");
		builder.append(" oauth_consumer_key=\"" + vo.getConsumerKey()+"\",");
		builder.append(" oauth_signature=\"" + OAuthUtil.encode(vo.getSignature())+"\",");
		builder.append(" oauth_version=\"" + vo.getVersion()+"\"");
		
		header.setValue(builder.toString());
		
		method.addRequestHeader(header);
		
		
		//System.out.println(header.toString());

		httpClient.executeMethod(method);
		result = method.getResponseBodyAsString();
		
		return result;
	}
	
	protected String requestResourceTokenWithParamType(ResourceTokenVO vo) throws Exception{

		String result = null;
				
		return result;
	}
	
	private NameValuePair[] makeParamStr(ResourceTokenVO vo){
		
		String param = vo.getParam();
		
		if(param == null){
			return null;
		}
		
		String[] arr = param.split("=");
		NameValuePair[] nvp = new NameValuePair[1];
			
		nvp[0] = new NameValuePair(arr[0], arr[1]);
		
		return nvp;
	}
	
	private String getParamData(ResourceTokenVO vo, int type){
		
		String param = vo.getParam();
		
		
		
		String[] arr = param.split("=");
		
		return arr[type];
	}
	
	
	protected String requestResourceTokenWithHeaderType(ResourceTokenVO vo) throws Exception{

		System.out.println("requestResourceTokenWithHeaderType");

		String result = null;

		HttpMethod method = null;
		
		if(vo.getMethod().equals("GET")){
			method = new GetMethod(vo.getRequestURL());

			
			
		}else if(vo.getMethod().equals("POST")){
			method = new PostMethod(vo.getRequestURL());

		}
		
		method.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		//OAuth oauth_token="165407408-Vn2DOR7KxIYriKs1lTTx0pBbiE2ElvHKKYveQRPe", oauth_consumer_key="GRiVVLOyXaEkf8gOolw0A", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1306458402", oauth_nonce="1306458402443449000", oauth_version="1.0", oauth_signature="86klL49D3Y%2FPXBgq02GElhEREmI%3D"
		
		Header header = new Header();

		header.setName("Authorization");
		
		StringBuilder builder = new StringBuilder();
		builder.append("OAuth ");
		builder.append(" oauth_token=\"" + vo.getRequestOauthToken()+"\",");
		builder.append(" oauth_consumer_key=\"" + vo.getConsumerKey()+"\",");
		builder.append(" oauth_signature_method=\"" + vo.getSignatureMethod()+"\",");
		builder.append(" oauth_timestamp=\"" + vo.getTimestamp()+"\",");
		
		builder.append(" oauth_nonce=\"" + vo.getNonce()+"\",");
		builder.append(" oauth_version=\"" + vo.getVersion()+"\",");
		builder.append(" oauth_signature=\"" + OAuthUtil.encode(vo.getSignature())+"\",");
		
		if(vo.getParam() != null){
		
			builder.append(" x_"+getParamData(vo, 0) +"=\""+ OAuthUtil.encode(getParamData(vo, 1))+"\",");
		}
		
		header.setValue(builder.toString());
		
		method.addRequestHeader(header);
		
		
		System.out.println(header.toString());
		
		

		httpClient.executeMethod(method);
		
		//System.out.println(method.);
		
		result = method.getResponseBodyAsString();
			
		if(method.getStatusCode() != 200){
			throw new Exception("OAuth Exception");
		}
			
		return result;
		

	}
	
	
	public static void main(String[] args) throws Exception {
		
//		TokenSender sender = new TokenSender(TokenSender.TYPE_HEADER);
//		
//		RequestTokenVO vo = new RequestTokenVO();
//		vo.setMethod("POST");
//		vo.setRequestURL("https://api.twitter.com/oauth/request_token");
//		vo.setCallbackURL("http://127.0.0.1:8080/w1/callback");
//		vo.setConsumerKey("GRiVVLOyXaEkf8gOolw0A");
//		vo.setConsumerSecretKey("HOsnL9rKe9mF7XrrhGIwE8IxPi7kV1r8cNI2aqiGLE");
//		//vo.setNonce("F8apg8");
//		//vo.setTimestamp("1306333444");
//		vo.sign();
//		
//		
//		//pnQTTeTU2FmB%2BVp%2Fez5IP945Hh0%3D
//		System.out.println(vo.getSignature());
//		
//		
//		sender.sendHttpClient(vo);
//		
//		System.out.println(vo.getRequestOauthToken());
//		System.out.println(vo.getRequestOauthTokenSecret());
		
		
//		//OAuth oauth_nonce="9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323047", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_token="8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc", oauth_verifier="pDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY", oauth_signature="PUw%2FdHA4fnlJYM6RhXk5IU%2F0fCc%3D", oauth_version="1.0"
//		//POST&https%3A%2F%2Fapi.twitter.com%2Foauth%2Frequest_token&oauth_callback%3Dhttp%253A%252F%252F127.0.0.1%253A8080%252Fw1%252Fcallback%26oauth_consumer_key%3DGRiVVLOyXaEkf8gOolw0A%26oauth_nonce%3D1306503063690112000%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1306503063%26oauth_version%3D1.0
		//7wSfySobpQTSZ0IcSyRyoOx0ZXI=
		
//		TokenSender sender = new TokenSender(TokenSender.TYPE_HEADER);
//		
//		AccessTokenVO vo = new AccessTokenVO();
//		vo.setMethod("POST");
//		vo.setRequestURL("https://api.twitter.com/oauth/access_token");
//		
//		vo.setConsumerKey("GDdmIQH6jhtmLUypg82g");
//		vo.setConsumerSecretKey("MCD8BKwGdgPHvAuvgvz4EQpqDAtx89grbuNMRd7Eh98");
//		vo.setNonce("9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8");
//		vo.setTimestamp("1272323047");
//		
//		vo.setRequestOauthToken("8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc");
//		vo.setRequestOauthTokenSecret("x6qpRnlEmW9JbQn4PQVVeVG8ZLPEx6A0TOebgwcuA");
//		vo.setVerifier("pDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY");
//		vo.sign();
//		
//		
//		
//		sender.sendHttpClient(vo);
		
		//OAuth oauth_nonce="9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323047", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_token="8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc", oauth_verifier="pDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY", oauth_signature="PUw%2FdHA4fnlJYM6RhXk5IU%2F0fCc%3D", oauth_version="1.0"
		//OAuth oauth_nonce="9zWH6qe0qG7Lc1telCn7FhUbLyVdjEaL3MO5uHxn8", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272323047", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_token="8ldIZyxQeVrFZXFOZH5tAwj6vzJYuLQpl0WUEYtWc", oauth_verifier="pDNg57prOHapMbhv25RNf75lVRd6JDsni1AJJIDYoTY", oauth_signature="PUw%2FdHA4fnlJYM6RhXk5IU%2F0fCc%3D", oauth_version="1.0"
		


		//OAuth oauth_nonce="oElnnMTQIZvqvlfXM56aBLAf5noGD0AQR3Fmi7Q6Y", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272325550", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_token="819797-Jxq8aYUDRmykzVKrgoLhXSq67TEa5ruc4GJC2rWimw", oauth_signature="yOahq5m0YjDDjfjxHaXEsW9D%2BX0%3D", oauth_version="1.0"
		//OAuth oauth_nonce="oElnnMTQIZvqvlfXM56aBLAf5noGD0AQR3Fmi7Q6Y", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272325550", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_token="819797-Jxq8aYUDRmykzVKrgoLhXSq67TEa5ruc4GJC2rWimw", oauth_signature="yOahq5m0YjDDjfjxHaXEsW9D%2BX0%3D", oauth_version="1.0"
		
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		vo.setRequestURL("http://twitter.com/statuses/friends_timeline.atom");
		
		vo.setConsumerKey("GRiVVLOyXaEkf8gOolw0A");
		vo.setConsumerSecretKey("HOsnL9rKe9mF7XrrhGIwE8IxPi7kV1r8cNI2aqiGLE");
		//vo.setNonce("1306458402443449000");
		//vo.setTimestamp("1306458402");
		
		vo.setRequestOauthToken("165407408-Vn2DOR7KxIYriKs1lTTx0pBbiE2ElvHKKYveQRPe");
		vo.setRequestOauthTokenSecret("kemBOsNsJkpqvJg4u78HAyOG5PbitZLvXeUBa06Lc");
		vo.sign();
		System.out.println(vo.getBaseString());
		
		
		
		System.out.println(vo.getSignature());
		
		TokenSender finalSender = new TokenSender(TokenSender.TYPE_HEADER);
		
		//finalSender.sendToResource(TYPE_HEADER, vo);
		
		finalSender.sendHttpClient(vo);

		
		System.out.println(vo.getResult());

		
	}




}
