package com.sds.hr.test;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestClient {
	public static void main(String[] args) throws Exception {

		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod("http://localhost:8000/ContentNegotiation/service/countries");
		method.addRequestHeader("ACCEPT", "application/xml");
		
		int statusCode = client.executeMethod(method);
		if (statusCode == 200) {
			String strData = method.getResponseBodyAsString();
			System.out.println(strData);
		}

		System.out.println(URLEncoder.encode("¹è½º","UTF-8"));
		
	}
}


