package org.thinker.oauth.provider.util;

import java.io.Serializable;

public class OAuthUserVO implements Serializable {

	private static final long serialVersionUID = -6904257382414277505L;
	private String consumerKey;
	private String consumerSecretKey;
	
	private String oauthToken;
	private String oauthTokenSecret;
	
	private String accssToken;
	private String accesTokenSecret;
	
	
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
	public String getOauthToken() {
		return oauthToken;
	}
	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}
	public String getOauthTokenSecret() {
		return oauthTokenSecret;
	}
	public void setOauthTokenSecret(String oauthTokenSecret) {
		this.oauthTokenSecret = oauthTokenSecret;
	}
	public String getAccssToken() {
		return accssToken;
	}
	public void setAccssToken(String accssToken) {
		this.accssToken = accssToken;
	}
	public String getAccesTokenSecret() {
		return accesTokenSecret;
	}
	public void setAccesTokenSecret(String accesTokenSecret) {
		this.accesTokenSecret = accesTokenSecret;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumerKey == null) ? 0 : consumerKey.hashCode());
		result = prime
				* result
				+ ((consumerSecretKey == null) ? 0 : consumerSecretKey
						.hashCode());
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
		OAuthUserVO other = (OAuthUserVO) obj;
		if (consumerKey == null) {
			if (other.consumerKey != null)
				return false;
		} else if (!consumerKey.equals(other.consumerKey))
			return false;
		if (consumerSecretKey == null) {
			if (other.consumerSecretKey != null)
				return false;
		} else if (!consumerSecretKey.equals(other.consumerSecretKey))
			return false;
		return true;
	}
	
	
	
	
}
