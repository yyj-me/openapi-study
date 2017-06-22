package org.thinker.oauth.controller;

import java.util.Map;

public class UserInfoVO {
	
	private String consumerKey;
	private String consumerSecretKey;
	
	private Map<String, String> urlMap;
	
	

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

	public Map<String, String> getUrlMap() {
		return urlMap;
	}

	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}
	
	
	
	

}
