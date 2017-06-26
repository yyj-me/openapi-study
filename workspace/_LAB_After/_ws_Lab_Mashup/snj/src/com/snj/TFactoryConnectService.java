package com.snj;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("TFactoryConnectService")
public class TFactoryConnectService {
	
	@Resource(name = "restTemplate")
	private RestTemplate restTemplate;
	
	private static Logger logger = Logger.getLogger(TFactoryConnectService.class);
	
	//tFactory site id
	//will by changed!!!!
	private static final String CONNECT_URL_CODE="http://localhost:8000/tFactory/model/open/json/{tcode}";
	
	
	public String connectTFactory(String code)throws Exception{
		
		if(code == null){
			throw new Exception("code value is null");
		}
		
		String result = restTemplate.getForObject(CONNECT_URL_CODE, String.class, code);

		
		return result;
	}

}
