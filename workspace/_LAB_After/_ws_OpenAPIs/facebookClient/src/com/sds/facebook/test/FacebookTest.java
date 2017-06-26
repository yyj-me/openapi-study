package com.sds.facebook.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class FacebookTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String url = "https://graph.facebook.com/oauth/authorize?client_id=215388178499545&redirect_uri=http://jcornor.com:8000/facebookClient/callback.html&type=user_agent&display=popup&scope=publish_stream,create_event,rsvp_event,sms,offline_access";
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		int statusCode = client.executeMethod(method);
		if (statusCode == 200) {
			String data = method.getResponseBodyAsString();
			System.out.println(data);
		}
		
	}

}
