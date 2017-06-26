package com.snj;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("TFactoryConnectService")
public class TFactoryConnectService {
	private static Logger logger = Logger.getLogger(TFactoryConnectService.class);
	
	private static final String CONNECT_URL_CODE = "http://tFactory.com:8000/tFactory/model/open/json/";
	
	public String connectTFactory(String code) throws HttpException, IOException {
		if(code == null || code.equals("")) {
			code = "all";
		}
		logger.info("### tcode : " + code);
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(CONNECT_URL_CODE + code);
		client.executeMethod(method);
		String json = method.getResponseBodyAsString();
		
		return json;
	}
}
