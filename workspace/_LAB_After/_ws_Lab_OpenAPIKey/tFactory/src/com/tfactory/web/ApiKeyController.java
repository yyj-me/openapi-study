package com.tfactory.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.openapi.ApiKeyProcessor;
import org.thinker.openapi.ApiKeyVO;




@Controller("ApiKeyController")
public class ApiKeyController {
	
	private static Logger logger = Logger.getLogger(ApiKeyController.class);
	
	@Resource(name="ApiKeyGenerator")
	private ApiKeyProcessor service;
	
	
	@RequestMapping(value="/key/request", method=RequestMethod.GET)
	public String startApiKey()throws Exception{
		
		logger.info("startApiKey...........");
		
		return "requestApiKey";
	}
	
	@RequestMapping(value="/key/makeKey", method=RequestMethod.POST)
	public ModelAndView makeApiKey(ApiKeyVO apiKeyVO)throws Exception{
		ModelAndView mav = new ModelAndView();
		
		logger.info("hostName: " + apiKeyVO.getHostName());
		
		String keyValue = service.requestNewAPIKey(apiKeyVO);
		logger.info("api key: "+ keyValue);
		mav.addObject("apikey", keyValue);
		mav.setViewName("keyResult");
		return mav;
	}
}
