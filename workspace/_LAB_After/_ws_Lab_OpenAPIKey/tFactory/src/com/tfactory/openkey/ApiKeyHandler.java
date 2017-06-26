package com.tfactory.openkey;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thinker.openapi.ApiKeyProcessor;
import org.thinker.openapi.ApiKeyVO;


public class ApiKeyHandler extends HandlerInterceptorAdapter {
	
	@Resource(name="ApiKeyGenerator")
	private ApiKeyProcessor keyProcessor;
	
	
	private static Logger logger = Logger.getLogger(ApiKeyHandler.class);
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		boolean result = false;
		
		//use for anywhere 
		String origin = request.getHeader("Origin");
		String apiKey = URLEncoder.encode(request.getParameter("key"),"UTF-8");
		
		logger.info("request origin:"  + origin);
		logger.info("request key:"  + apiKey);
		
		if(origin != null){
		
			ApiKeyVO vo = new ApiKeyVO();
			vo.setApiKey(apiKey);
			vo.setHostName(origin);
		
			keyProcessor.checkApiKey(origin, apiKey);
			result = true;
		}
		
		
		return result;
	}

	
	
	
}
