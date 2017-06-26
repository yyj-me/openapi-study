package org.thinker.oauth.provider.parser;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.thinker.oauth.provider.util.OAuthException;

public class PrameterParser extends OAuthParser {

	private HttpServletRequest request;
	
	
	
	public PrameterParser(HttpServletRequest request) {
		super();
		this.request = request;
	}



	@Override
	public void parsing() throws OAuthException {
		
		Map paramMap =  request.getParameterMap();
		
		Iterator it = paramMap.keySet().iterator();
		
		while(it.hasNext()){
			
			String key =(String) it.next();
			String[] value = (String[])paramMap.get(key);
			dataMap.put(key, value[0]);
			
		}
		

	}

}
