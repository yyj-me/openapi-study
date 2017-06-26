package org.thinker.oauth.generator;

import java.io.Serializable;

public class OAuthTokenVO implements Serializable{
	
	private static final long serialVersionUID = 1351625242120078529L;
	
	
	private String consumerKey;
	private String consumerSecretKey;
	
	private String requestToken;
	private String requestTokenSecret;
	
	private String accessToken;
	private String accessTokenSecret;
	
	private String verifier;
	
	public OAuthTokenVO(String consumerKey){
		this.consumerKey = consumerKey;
	}
	
	public String getConsumerKey() {
		return consumerKey;
	}
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	public String getConsumerSecretKey() {
		return consumerSecretKey;
	}
	public void setConsumerSecretKey(String consumerSecretKey) {
		this.consumerSecretKey = consumerSecretKey;
	}
	public String getRequestToken() {
		return requestToken;
	}
	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	public String getRequestTokenSecret() {
		return requestTokenSecret;
	}
	public void setRequestTokenSecret(String requestTokenSecret) {
		this.requestTokenSecret = requestTokenSecret;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}
	
	
	
	
	
	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumerKey == null) ? 0 : consumerKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OAuthTokenVO other = (OAuthTokenVO) obj;
		
		
		
		if (consumerKey == null) {
			if (other.consumerKey != null){
				return false;
			}
		} else if (!consumerKey.equals(other.consumerKey)){
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "consumerKey=" + consumerKey + ", \nconsumerSecretKey="
				+ consumerSecretKey + ", \nrequestToken=" + requestToken
				+ ", \nrequestTokenSecret=" + requestTokenSecret
				+ ", \naccessToken=" + accessToken + ", \naccessTokenSecret="
				+ accessTokenSecret + ", \nverifier=" + verifier;
	}
	
}