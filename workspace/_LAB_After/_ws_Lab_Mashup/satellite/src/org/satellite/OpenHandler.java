package org.satellite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class OpenHandler extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(OpenHandler.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		boolean result = true;
		
		//use for anywhere 
		String origin = request.getHeader("Origin");
		
		logger.info("request "  + origin);
		
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
		//response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("origin : " + origin);
		
		//use constraint by open api key 
		//not yet
		
		
		
		
		return result;
	}
}
