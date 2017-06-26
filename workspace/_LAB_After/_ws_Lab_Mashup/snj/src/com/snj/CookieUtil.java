package com.snj;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

public class CookieUtil {
	
	
	public static void addCookie(HttpServletResponse response, String name, String value){
		
		Cookie ck = null;
		ck = new Cookie(name, Base64.encodeBase64String(value.getBytes()));
		response.addCookie(ck);
	}
	
	
	public static String findValueByName(Cookie[] cookies, String cookieName){
		
		for(Cookie ck: cookies){
			
			if(ck.getName().equals(cookieName)){
				try {
					return new String(Base64.decodeBase64(ck.getValue().getBytes()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return null;
		
	}
	
	public static void changeCookieValue(Cookie[] cookies, String cookieName, String newValue, HttpServletResponse response){
		
		
		for(Cookie ck: cookies){
			
			if(ck.getName().equals(cookieName)){
				
				System.out.println("CHANGE COOKIE VALUE:" +cookieName+":" +Base64.encodeBase64String(newValue.getBytes()));
				

				
				response.addCookie(new Cookie(cookieName, Base64.encodeBase64String(newValue.getBytes())));
				
			}
			
		}

		
	}
	
	
	public static Cookie findByName(Cookie[] cookies, String cookieName){
		
		for(Cookie ck: cookies){
			
			if(ck.getName().equals(cookieName)){
				return ck;
			}
			
		}
		return null;
		
	}
}
