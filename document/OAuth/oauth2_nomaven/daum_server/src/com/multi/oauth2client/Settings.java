package com.multi.oauth2client;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Settings {
	public static final String CLIENT_ID = "8099378101188452483";
	public static final String CLIENT_KEY = "8f4b8c4e69fe745122686d5857593323";
	public static final String AUTHORIZE_URL="https://apis.daum.net/oauth2/authorize";
	public static final String ACCES_TOKEN_URL = "https://apis.daum.net/oauth2/token";
	public static final String RESPONSE_TYPE = "code";
	public static final String REDIRECT_URI = "http://jcornor.com:8080/daum_server/callback.jsp";
	public static final String PERSONAL_INFO_URL = "https://apis.daum.net/user/v1/show.json";

	public static String getParamString(HashMap<String,String> map, boolean useAuthHeader) throws Exception {
		int i=0;
		String strout = "";
		for (String key : map.keySet()) {
			if (useAuthHeader == true && 
					(key.equals("client_id") || key.equals("client_secret") )) {
				continue;
			}
			if (i !=0 )	strout += "&";
			strout += key + "=" + URLEncoder.encode(map.get(key), "utf-8");
			i++;
		}
				
		return strout;
	}
	
	public static String generateRandomState() {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			String base = ""+System.currentTimeMillis();
			byte[] hash = sha1.digest(base.getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
