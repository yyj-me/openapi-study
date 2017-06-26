package com.sds.hr.test;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class TestClient {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
//		Country c = new Country("KO", "Korea", "Asia");
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String strJson = mapper.writeValueAsString(c);
//		System.out.println(strJson);
//		
//		strJson = strJson.replaceAll("Korea", "England");
//		Country c2 = mapper.readValue(strJson, Country.class);
		
		//1. HttpClient GET
//		HttpClient client = new HttpClient();
//		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//		GetMethod method = new GetMethod("http://jcornor.com:8000/SpringMVCService/service/countries.json?callback=test");
//		int statusCode = client.executeMethod(method);
//		if (statusCode == 200) {
//			String strData = method.getResponseBodyAsString();
//			System.out.println(strData);
//		}
		
		//1. HttpClient POST 1
//		HttpClient client = new HttpClient();
//		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//		
//		PostMethod method = new PostMethod("http://jcornor.com:8000/SpringMVCService/service/countries/AR.xml");
//		method.setRequestHeader("Content-type","text/xml");
//		method.setRequestBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><country country_id=\"AR\"><country_name>Argentina</country_name><region_name>Americas</region_name></country>");
//		int statusCode = client.executeMethod(method);
//		if (statusCode == 200) {
//			String strData = method.getResponseBodyAsString();
//			System.out.println(strData);
//		}
		
		//2. HttpClient POST 2
//		HttpClient client = new HttpClient();
//		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//
//		PostMethod method = new PostMethod("http://jcornor.com:8000/SpringMVCService/service/test.do");
//		method.addParameter("age", "18");
//		method.addParameter("userid", "gdhong");
//		method.addParameter("name", URLEncoder.encode("ȫ�浿", "UTF-8"));
//		
//		int statusCode = client.executeMethod(method);
//		if (statusCode == HttpStatus.SC_OK) {
//			String strData = method.getResponseBodyAsString();
//		}
		
		
	}
}


