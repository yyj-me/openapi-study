package org.thinker.oauth;


public class ResourceTokenVO extends AccessTokenVO {
	
	
	
	private String param;
	
	private String paramKey;
	private String paramValue;
	
	private String paramValueConverted;
	
	private String result;
	
	


	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getParam() {
		return param;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamValueConverted() {
		return paramValueConverted;
	}
	public void setParamValueConverted(String paramValueConverted) {
		this.paramValueConverted = paramValueConverted;
	}
	public void setParam(String param) {
		this.param = param;
		
		String[] tokens = param.split("=");
		
		this.paramKey= tokens[0];
		this.paramValue = tokens[1];
		
		this.paramValueConverted = changeStr(paramValue);
		
		
		
	}

	private String changeStr(String str){
		
		String[] arr = str.split(" ");
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < arr.length ; i++){
			
			builder.append(OAuthUtil.encode(arr[i]));
			
			
			if(i != (arr.length -1)){
				builder.append("%20");
			}
			
			
		}
		
		return builder.toString();
	}




	@Override
	public void sign() {
		
		StringBuilder builder = new StringBuilder();
		builder.append(this.getMethod() +"&");
		builder.append(OAuthUtil.encode(this.getRequestURL())+"&");

		builder.append(OAuthUtil.encode("oauth_consumer_key="+OAuthUtil.encode(this.getConsumerKey())+"&"));
		builder.append(OAuthUtil.encode("oauth_nonce="+OAuthUtil.encode(this.getNonce())+"&"));
		builder.append(OAuthUtil.encode("oauth_signature_method="+OAuthUtil.encode(this.getSignatureMethod())+"&"));
		builder.append(OAuthUtil.encode("oauth_timestamp="+OAuthUtil.encode(this.getTimestamp())+"&"));
		builder.append(OAuthUtil.encode("oauth_token="+OAuthUtil.encode(this.getRequestOauthToken())+"&"));
		builder.append(OAuthUtil.encode("oauth_version="+OAuthUtil.encode(this.getVersion())));
		if(this.getParamKey() != null){
			builder.append(OAuthUtil.encode("&x_"+this.paramKey+"="+paramValueConverted ));
		}
		
		this.setBaseString(builder.toString());
		//System.out.println(builder.toString());
		
		HmacSHA1 sha1 = new HmacSHA1();
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

	}
	
	public static void main(String[] args) {
		
		
	    //POST&http%3A%2F%2Fapi.twitter.com%2F1%2Fstatuses%2Fupdate.json&oauth_consumer_key%3DGDdmIQH6jhtmLUypg82g%26oauth_nonce%3DoElnnMTQIZvqvlfXM56aBLAf5noGD0AQR3Fmi7Q6Y%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1272325550%26oauth_token%3D819797-Jxq8aYUDRmykzVKrgoLhXSq67TEa5ruc4GJC2rWimw%26oauth_version%3D1.0%26status%3Dsetting%2520up%2520my%2520twitter%2520%25E7%25A7%2581%25E3%2581%25AE%25E3%2581%2595%25E3%2581%2588%25E3%2581%259A%25E3%2582%258A%25E3%2582%2592%25E8%25A8%25AD%25E5%25AE%259A%25E3%2581%2599%25E3%2582%258B
	    //POST&http%3A%2F%2Fapi.twitter.com%2F1%2Fstatuses%2Fupdate.json&oauth_consumer_key%3DGDdmIQH6jhtmLUypg82g%26oauth_nonce%3DoElnnMTQIZvqvlfXM56aBLAf5noGD0AQR3Fmi7Q6Y%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1272325550%26oauth_token%3D819797-Jxq8aYUDRmykzVKrgoLhXSq67TEa5ruc4GJC2rWimw%26oauth_version%3D1.0%26status%3Dsetting%2520up%2520my%2520twitter%2520%25E7%25A7%2581%25E3%2581%25AE%25E3%2581%2595%25E3%2581%2588%25E3%2581%259A%25E3%2582%258A%25E3%2582%2592%25E8%25A8%25AD%25E5%25AE%259A%25E3%2581%2599%25E3%2582%258B
		
		//MCD8BKwGdgPHvAuvgvz4EQpqDAtx89grbuNMRd7Eh98&J6zix3FfA9LofH0awS24M3HcBYXO5nI1iYe8EfBA
		//MCD8BKwGdgPHvAuvgvz4EQpqDAtx89grbuNMRd7Eh98&J6zix3FfA9LofH0awS24M3HcBYXO5nI1iYe8EfBA
		//yOahq5m0YjDDjfjxHaXEsW9D+X0=

		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		vo.setRequestURL("http://twitter.com/statuses/friends_timeline.atom");
		
		vo.setConsumerKey("GRiVVLOyXaEkf8gOolw0A");
		vo.setConsumerSecretKey("HOsnL9rKe9mF7XrrhGIwE8IxPi7kV1r8cNI2aqiGLE");
		vo.setNonce("1306458402443449000");
		vo.setTimestamp("1306458402");
		
		vo.setRequestOauthToken("165407408-Vn2DOR7KxIYriKs1lTTx0pBbiE2ElvHKKYveQRPe");
		vo.setRequestOauthTokenSecret("kemBOsNsJkpqvJg4u78HAyOG5PbitZLvXeUBa06Lc");

		//vo.setParam("status=setting up my twitter 私のさえずりを設定する");
		vo.sign();
		
		//OAuth oauth_nonce="oElnnMTQIZvqvlfXM56aBLAf5noGD0AQR3Fmi7Q6Y", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272325550", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_token="J6zix3FfA9LofH0awS24M3HcBYXO5nI1iYe8EfBA", oauth_signature="yOahq5m0YjDDjfjxHaXEsW9D%2BX0%3D", oauth_version="1.0"
		//OAuth oauth_nonce="oElnnMTQIZvqvlfXM56aBLAf5noGD0AQR3Fmi7Q6Y", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1272325550", oauth_consumer_key="GDdmIQH6jhtmLUypg82g", oauth_token="819797-Jxq8aYUDRmykzVKrgoLhXSq67TEa5ruc4GJC2rWimw", oauth_signature="yOahq5m0YjDDjfjxHaXEsW9D%2BX0%3D", oauth_version="1.0"
		    
		
		
		System.out.println(vo.getBaseString());
		
		
		
		System.out.println(vo.getSignature());
		
		//yOahq5m0YjDDjfjxHaXEsW9D+X0=
		//yOahq5m0YjDDjfjxHaXEsW9D+X0=
		

	}

}
