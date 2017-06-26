package com.multi.tistorytest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class Settings {
	public static final String CLIENT_ID = "8e59b1061b9ad359788565442498c447";
	public static final String CLIENT_KEY = "586a35ef3eb8d11733a3da95b4af2b059da4dad1a79e08cf9b8344377b0cceaf2daf431d";
	public static final String AUTHORIZE_URL="https://www.tistory.com/oauth/authorize";
	public static final String ACCES_TOKEN_URL = "https://www.tistory.com/oauth/access_token";
	public static final String REDIRECT_URI = "http://localhost:8000/tistory/callback.jsp";
	public static final String BLOG_INFO_URL = "https://www.tistory.com/apis/blog/info";
	
	public static String getParamString(HashMap<String,String> map) throws Exception {
		int i=0;
		String strout = "";
		for (String key : map.keySet()) {
			if (i !=0 )	strout += "&";
			strout += key + "=" + URLEncoder.encode(map.get(key), "utf-8");
			i++;
		}
		
		return strout;
	}
}
