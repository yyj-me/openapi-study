package com.tfactory.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.thinker.oauth.provider.parser.HeaderParser;
import org.thinker.oauth.provider.parser.OAuthParser;
import org.thinker.oauth.provider.util.OAuthException;

import com.tfactory.model.ModelException;
import com.tfactory.model.ModelVO;
import com.tfactory.oauth.OAuthTokenStorage;
import com.tfactory.oauth.OAuthTokenVO;
import com.tfactory.service.ModelService;

@Controller
public class ResourceTokenHandler {
	
	private static Logger logger = Logger.getLogger(ResourceTokenHandler.class);
	
	@Resource(name="jsonTemplate")
	private View jsonView;
	
	@Resource(name="xmlTemplate")
	private View xmlView;
	
	@Resource(name="ModelService")
	private ModelService service;

	
	@Resource(name="oauthStorage")
	private OAuthTokenStorage oauthStorage;
	



	@RequestMapping("/resource")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response)throws OAuthException, ModelException{
		
		System.out.println("\n\n ResourceTokenHandler");
		
		String requestConsumerKey = HeaderParser.findHeaderString(request.getHeader("Authorization"), "oauth_consumer_key");
		
		String mcode = HeaderParser.findHeaderString(request.getHeader("Authorization"), "x_mcode");
		
		if(mcode != null){
			
			System.out.println("MCODE: " + mcode);
		}
		
		System.out.println("requestConsumerKey:"+ requestConsumerKey);
		
		OAuthTokenVO tokenVO  = oauthStorage.getToken(requestConsumerKey);
		
		
		
		String method = request.getMethod();
		String requestURL = "http://"+request.getServerName() +":"+ request.getServerPort()+ request.getRequestURI();
		String headerValue = request.getHeader("Authorization");
		
		
		
		String consumerSecretKey = tokenVO.getConsumerSecretKey();
		String oauthTokenSecretKey = tokenVO.getAccessTokenSecret();
		
		System.out.println("method : " + method);
		System.out.println("requestURL :" + requestURL);
		System.out.println("headerValue:" + headerValue);
		System.out.println("consumerSecretKey : " + consumerSecretKey);
		System.out.println("oauthTokenSecretKey : " + oauthTokenSecretKey);
		
		System.out.println("user : " + request.getSession().getAttribute("user"));
		
		
		
		OAuthParser parser  = new HeaderParser(method, requestURL, headerValue, consumerSecretKey,oauthTokenSecretKey);
		
		
		logger.info("getJsonModelS" );
		
//		response.setHeader("Cache-Control", "no-cache");  
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		
		ModelAndView mav = new ModelAndView();
		
		if (mcode == null) {
			List<ModelVO> list = service.viewAllModels();
			mav.addObject("list",list);
			mav.setView(jsonView);
		} else {
			ModelVO vo = service.viewModel(mcode);
			mav.addObject("modelVO",vo);
			mav.setView(jsonView);			
		}
		
		
		return mav;
		
	}
}
